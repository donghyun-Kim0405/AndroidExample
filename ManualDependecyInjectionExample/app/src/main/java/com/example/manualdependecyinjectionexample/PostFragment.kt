package com.example.manualdependecyinjectionexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.manualdependecyinjectionexample.container.AppContainer
import com.example.manualdependecyinjectionexample.container.PostContainer
import com.example.manualdependecyinjectionexample.databinding.FragmentPostBinding
import java.util.*

class PostFragment : Fragment() {

    private lateinit var binding : FragmentPostBinding
    private lateinit var appContainer : AppContainer
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //흐름컨테이너 주기 제어
        appContainer = (requireActivity().application as CustomApplication).appContainer
        appContainer.postContainer = PostContainer(appContainer.postRepository)
        postViewModel = appContainer.postContainer?.postViewModelFactory!!.create()

    }   //onCreate()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)
        return binding.root
    }   //onCreateView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel.mutableLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        binding.btnLocalData.setOnClickListener {
            postViewModel.getLocalData()
        }

        binding.btnRemoteData.setOnClickListener {
            postViewModel.getRemoteData()
        }


    }   //onViewCreated()



    override fun onDestroy() {
        //흐름컨테이너 주기 제어
        appContainer.postContainer = null
        super.onDestroy()
    }//onDestroy()



}