package com.example.feedme.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent

import com.example.feedme.R
import com.example.feedme.models.AnswerOption
import com.example.feedme.models.Question
import com.example.feedme.ui.FeedbackScreen
import com.example.feedme.viewmodels.SettingsViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SettingsFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var retrofit: Retrofit

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.local_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val layoutInflater = inflater.inflate(R.layout.settings_fragment, container, false)

        (layoutInflater as ViewGroup).setContent {
           arrayListOf(        Question("", false, "How are you?",
               arrayListOf(AnswerOption("", "Good"), AnswerOption("", "Bad"))))
        }

        /*val questionBtn = layoutInflater.findViewById<Button>(R.id.button)
        questionBtn.setOnClickListener {

            val roomId = "5e07526f6151b96aacb4a637"
            val questionService = retrofit.create(QuestionService::class.java)
            val call = questionService.getQuestions("jsonwebtoken", roomId)

            call.enqueue(object : Callback<ArrayList<Question>> {
                override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {
                    Log.i("YO", "HEJ")
                    Log.i("YO", t.toString())
                }

                override fun onResponse(
                    call: Call<ArrayList<Question>>,
                    response: Response<ArrayList<Question>>
                ) {
                    val questions = response.body()
                    if (questions != null)
                        listener?.onFragmentInteraction(questions)
                }
            })
        }*/
        return layoutInflater
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SettingsFragment.OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(questions: ArrayList<Question>)
    }
}
