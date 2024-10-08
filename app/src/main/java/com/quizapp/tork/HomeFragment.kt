package com.quizapp.tork

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.quizapp.tork.adapter.CategoryAdapter
import com.quizapp.tork.model.Category
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val database = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser?.displayName
        val recycler = view.findViewById<RecyclerView>(R.id.cardView)
        val data = ArrayList<Category>()
        val adapter = CategoryAdapter(data)
        view.uname.text = user
        Log.i(TAG,"user $user")
        database.collection("categories")
            .addSnapshotListener{ snapshot, _ ->
                data.clear()
                snapshot?.documents?.forEach{ documentSnapshot ->
                    val category: Category? = documentSnapshot.toObject(Category::class.java)
                    category?.cat_id = documentSnapshot.id
                    data.add(category!!)
                }
                adapter.notifyDataSetChanged()
            }

        recycler.layoutManager = GridLayoutManager(context,2)
        recycler.adapter = adapter
        return  view
    }
}
