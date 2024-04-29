package kr.cosine.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.cosine.search.databinding.ItemSearchBinding
import kr.cosine.search.infrastructure.persistence.entity.ImageDocumentEntity

class SearchAdapter(
    private val imageDocumentEntities: ArrayList<ImageDocumentEntity> = arrayListOf(),
    private val hideSwitch: Boolean = false,
    private val switchScope: (ImageDocumentEntity, Boolean) -> Unit = { _, _ -> },
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val glide = Glide.with(binding.root)

        init {
            binding.bookmarkSwitch.apply {
                if (hideSwitch) {
                    visibility = View.GONE
                    return@apply
                }
                setOnCheckedChangeListener { _, isChecked ->
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        switchScope(imageDocumentEntities[position], isChecked)
                    }
                }
            }
        }

        fun bind(imageDocumentEntity: ImageDocumentEntity) = with(binding) {
            glide.load(imageDocumentEntity.imageUrl).into(previewImageView)
            titleTextView.text = imageDocumentEntity.displaySitename
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int = imageDocumentEntities.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val imageDocumentEntity = imageDocumentEntities[position]
        holder.bind(imageDocumentEntity)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(imageDocumentEntities: List<ImageDocumentEntity>) {
        this.imageDocumentEntities.clear()
        this.imageDocumentEntities.addAll(imageDocumentEntities)
        notifyDataSetChanged()
    }
}