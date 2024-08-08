package com.ilmiddin1701.restapi_get_post_put_delete.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ilmiddin1701.restapi_get_post_put_delete.databinding.ItemRvBinding
import com.ilmiddin1701.restapi_get_post_put_delete.models.GetToDoResponse

class RvAdapter(var rvAction: RvAction, private var list: ArrayList<GetToDoResponse>): Adapter<RvAdapter.Vh>() {

    inner class Vh(private var itemRvBinding: ItemRvBinding): ViewHolder(itemRvBinding.root){
        fun onBind(getToDoResponse: GetToDoResponse, position: Int) {
            itemRvBinding.apply {
                btnMore.setOnClickListener {
                    rvAction.moreClick(getToDoResponse, position, btnMore)
                }
                tvSarlavha.text = "Sarlavha: " + getToDoResponse.sarlavha
                tvOxirgiMuddat.text = "Oxirgi muddat: " + getToDoResponse.oxirgi_muddat
                tvZarurlik.text = "Zarurlik: " + getToDoResponse.zarurlik
                tvBajarildi.text = if (getToDoResponse.bajarildi) "Bajarilgan✅" else "Bajarilmagan❌"
                tvSana.text = "Bugungi sana: " + getToDoResponse.sana
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    interface RvAction {
        fun moreClick(getToDoResponse: GetToDoResponse, position: Int, image: ImageView)
    }
}