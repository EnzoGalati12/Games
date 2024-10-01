package br.com.fiap.boardgames

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.bumptech.glide.Glide
import com.example.games.BoardGame
import com.example.games.BoardGameApplication
import com.example.games.MainViewModel
import com.example.games.MainViewModelFactory
import com.example.games.databinding.ActivityMainBinding
import com.example.games.databinding.DialogEditBoardGameBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as BoardGameApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpLogo()
        setUpListeners()
        setUpRecyclerView()
    }

    private fun setUpLogo() {
        Glide
            .with(this)
            .load("https://static.vecteezy.com/system/resources/previews/006/404/900/original/board-game-logo-free-vector.jpg")
            .into(binding.imageLogo)
    }


    private fun setUpListeners() {
        binding.buttonAddGame.setOnClickListener {
            val gameName = binding.editTextGameName.text.toString()
            val gameDescription = binding.editTextGameDescription.text.toString()
            val gameImageURL = binding.editTextGameImageUrl.text.toString()
            if (gameName.isNotBlank() && gameDescription.isNotBlank()) {
                mainViewModel.insert(
                    BoardGame(
                        name = gameName,
                        description = gameDescription,
                        imageURL = gameImageURL
                    )
                )
                binding.editTextGameName.text.clear()
                binding.editTextGameDescription.text.clear()
                binding.editTextGameImageUrl.text.clear()
                binding.editTextGameName.requestFocus()
            }
        }
    }


    private fun setUpRecyclerView() {
        val adapter = MainListAdapter(
            onEditClick = { game ->

            },
            onDeleteClick = { game -> mainViewModel.delete(game) }
        )
        binding.recyclerViewGames.adapter = adapter
        //binding.recyclerViewGames.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewGames.layoutManager = GridLayoutManager(this, 2)

        // Adicionar Divider
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerViewGames.context,
            (binding.recyclerViewGames.layoutManager as LinearLayoutManager).orientation
        )
        binding.recyclerViewGames.addItemDecoration(dividerItemDecoration)

        mainViewModel.allBoardGames.observe(this) { games ->
            games?.let { adapter.setBoardGames(it) }
        }
    }





}