package com.example.fragmenttest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttest.Communicator
import com.example.fragmenttest.R
import com.example.fragmenttest.adapter.RVAdapter
import com.example.fragmenttest.RetrofitServices
import com.example.fragmenttest.datamodel.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment: Fragment(), Communicator {

    private val baseUrl = "https://newsapi.org/"
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_news, container, false)

        startNews()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    private fun startNews() {
        recyclerView = RecyclerView(requireContext())

        val api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitServices::class.java)

        api.getNews().enqueue(object: Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.body() != null) {
                    adapter = RVAdapter(context!!, response.body()!!.articles, this@NewsFragment)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast
                    .makeText(context, "Something went wrong. Try to check your internet connection.", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    override fun passData(position: Int, name: String, image: String, url: String) {
        val bundle = Bundle()
        bundle.putInt("input_pos", position)
        bundle.putString("input_name", name)
        bundle.putString("input_image", image)
        bundle.putString("input_url", url)

        val frag2 = ArticleFragment()
        frag2.arguments = bundle

        this.parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out
            )
            .replace(R.id.newsFragment, frag2)
            .addToBackStack(null)
            .commit()

    }

}