package br.com.fiap.boardgames

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.games.BoardGame
import com.example.games.R
import com.example.games.databinding.BoardGameItemBinding

class MainListAdapter(
    private val onEditClick: (BoardGame) -> Unit,
    private val onDeleteClick: (BoardGame) -> Unit
) : RecyclerView.Adapter<MainListAdapter.GameViewHolder>() {

    private var boardGames: List<BoardGame> = emptyList()

    class GameViewHolder(val binding: BoardGameItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = BoardGameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }


    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentBoardGame = boardGames[position]
        holder.binding.textViewGameName.text = currentBoardGame.name
        holder.binding.textViewGameDescription.text = currentBoardGame.description

        Glide
            .with(holder.itemView.context)
            .load(currentBoardGame.imageURL)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imageViewGame)

        holder.binding.buttonEdit.setOnClickListener { onEditClick(currentBoardGame) }
        holder.binding.buttonDelete.setOnClickListener { onDeleteClick(currentBoardGame) }
    }

    override fun getItemCount() = boardGames.size

    fun setBoardGames(boardGames: List<BoardGame>) {
        this.boardGames = boardGames
        notifyDataSetChanged()
    }
}
