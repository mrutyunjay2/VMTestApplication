package mtj.example.vmapplication.UI.roomBookingModule

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mtj.example.vmapplication.R
import mtj.example.vmapplication.data.remote.response.RoomResponse

class RoomBookingAdapter(private val  dataList: List<RoomResponse>): RecyclerView.Adapter<RoomBookingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_book_layout, parent, false)

        return RoomBookingAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = dataList[position]
        //holder.slNo.text = "S.No :" +ItemsViewModel.id
        holder.Roomname.text = "Room Name :" + ItemsViewModel.name
        holder.capacity.text =  "Capacity :" +ItemsViewModel.max_occupancy
        if(ItemsViewModel.is_occupied){
            holder.availability_sts.setBackgroundColor(Color.RED)
            holder.availability.text = "Not Available"
            holder.availability.setTextColor(Color.RED)
        }
        else {
            holder.availability_sts.setBackgroundColor(Color.GREEN)
            holder.availability.text = "Available"
            holder.availability.setTextColor(Color.GREEN)
        }
    }

    override fun getItemCount(): Int {
       return dataList.size
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val Roomname: TextView = itemView.findViewById(R.id.room_name)
        val capacity: TextView = itemView.findViewById(R.id.capacity)
        val availability: TextView = itemView.findViewById(R.id.aval)
        val availability_sts: LinearLayout = itemView.findViewById(R.id.layoutImageProduct)


    }

}