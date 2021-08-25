package mtj.example.vmapplication.UI.EmpDetailsModule

import android.app.Dialog
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mtj.example.vmapplication.R
import mtj.example.vmapplication.data.remote.response.PeopleResponse
import java.util.*


class EmpDetailsAdapter(private var dataList: List<PeopleResponse>): RecyclerView.Adapter<EmpDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.emp_details_layout, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = dataList[position]
        holder.slNo.text = "Sl No:" +ItemsViewModel.id
        holder.name.text = "Name :" +ItemsViewModel.firstName + ItemsViewModel.lastName
        holder.mob.text =  "Mob.No :" +ItemsViewModel.phone
        holder.jobTitle.text = "Job Title :" + ItemsViewModel.jobTitle
        holder.email.text = "Email :" + ItemsViewModel.email
        val favcolor: Int = Color.parseColor(ItemsViewModel.favouriteColor)
        holder.favColor.setBackgroundColor(favcolor)
        val img_url = ItemsViewModel.avatar
        holder.itemView.setOnClickListener {

            val dialog = Dialog(it.context,android.R.style.Theme_Light)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.details_employee_dilog)
            val dilogProfile = dialog.findViewById(R.id.dilog_profile) as LinearLayout
            dilogProfile.setBackgroundColor(favcolor)
            val name = dialog.findViewById(R.id.name) as TextView
            name.text =ItemsViewModel.firstName+ItemsViewModel.lastName
            val email = dialog.findViewById(R.id.email) as TextView
            email.text = holder.email.text
            val jobTitle = dialog.findViewById(R.id.jobTitle) as TextView
            jobTitle.text = holder.jobTitle.text
            val mobile_no = dialog.findViewById(R.id.mobile_no) as TextView
            val address = dialog.findViewById(R.id.location) as TextView
            val profoileImg = dialog.findViewById(R.id.card_view_image) as ImageView
            mobile_no.text = holder.mob.text
            val favouriteColor = dialog.findViewById(R.id.favouriteColor) as TextView
                favouriteColor.setBackgroundColor(favcolor)
            val cancel =  dialog.findViewById(R.id.cancel) as TextView
            cancel.setOnClickListener { dialog.dismiss() }

            Glide.with(it.context)
                .load(img_url)
                .placeholder(R.drawable.ic_baseline_person_24)
                .error(R.drawable.ic_baseline_person_24)
                .into(profoileImg);

            val geocoder: Geocoder
            var addresses: List<Address?>
            geocoder = Geocoder(it.context, Locale.getDefault())
            addresses = geocoder.getFromLocation(ItemsViewModel.latitude.toDouble(), ItemsViewModel.longitude.toDouble(), 1);
            val city:String = addresses.get(0)?.locality.toString()
            val country:String = addresses.get(0)?.countryName.toString()
            address.text = city + "\n"+country
            dialog.show()
        }

        //
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        val slNo: TextView = itemView.findViewById(R.id.SNoTv)
        val name: TextView = itemView.findViewById(R.id.name)
        val jobTitle: TextView = itemView.findViewById(R.id.jobTitle)
        val mob: TextView = itemView.findViewById(R.id.mobile_no)
        val email: TextView = itemView.findViewById(R.id.email)
        val favColor:TextView = itemView.findViewById(R.id.favouriteColor)


    }





}