package com.aleks.aleksiev.codewars.presentation.challenges

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aleks.aleksiev.codewars.R
import com.aleks.aleksiev.codewars.databinding.LayoutCompletedChallengeBinding
import com.aleks.aleksiev.codewars.databinding.LayoutNetworkStateBinding
import com.aleks.aleksiev.codewars.presentation.challenges.model.ChallengeModel
import com.aleks.aleksiev.codewars.presentation.challenges.model.LoadingStateModel
import com.aleks.aleksiev.codewars.presentation.diffutils.DiffUtilsCallback
import com.aleks.aleksiev.codewars.utils.ItemClicked
import com.aleks.aleksiev.codewars.utils.NetworkState
import kotlinx.android.synthetic.main.layout_network_state.view.retryLoadingButton
import javax.inject.Inject

typealias RetryCallback = () -> Unit

class ChallengesAdapter @Inject constructor() : PagedListAdapter<ChallengeModel, RecyclerView.ViewHolder>(DiffUtilsCallback<ChallengeModel>()) {

    private val retryCallback: RetryCallback? = null
    private var networkState: NetworkState? = null
    var itemClickedHandler: ItemClicked<ChallengeModel?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.layout_completed_challenge -> {
                val binding = LayoutCompletedChallengeBinding.inflate(inflater, parent, false)
                ChallengeViewHolder(binding).also { vh ->
                    vh.itemView.setOnClickListener {
                        val position = vh.adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
                            itemClickedHandler?.onItemClicked(getItem(position))
                        }
                    }
                }
            }
            R.layout.layout_network_state -> {
                val binding = LayoutNetworkStateBinding.inflate(inflater, parent, false)
                val loadingStateVH = LoadingStateViewHolder(binding)
                loadingStateVH.itemView.retryLoadingButton.setOnClickListener {
                    retryCallback?.invoke()
                }
                loadingStateVH
            }
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.layout_completed_challenge -> (holder as ChallengeViewHolder).bind(getItem(position))
            R.layout.layout_network_state -> (holder as LoadingStateViewHolder).bind(LoadingStateModel(networkState))
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.NetworkStateSuccess
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.layout_network_state
        } else {
            R.layout.layout_completed_challenge
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        currentList?.let {
            val previousState = this.networkState
            val hadExtraRow = hasExtraRow()
            this.networkState = newNetworkState
            val hasExtraRow = hasExtraRow()
            if (hadExtraRow != hasExtraRow) {
                if (hadExtraRow) {
                    notifyItemRemoved(super.getItemCount())
                } else {
                    notifyItemInserted(super.getItemCount())
                }
            } else if (hasExtraRow && previousState !== newNetworkState) {
                notifyItemChanged(itemCount - 1)
            }
        }
    }
}