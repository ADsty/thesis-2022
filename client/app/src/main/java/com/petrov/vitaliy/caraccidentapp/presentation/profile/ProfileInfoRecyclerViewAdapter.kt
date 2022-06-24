package com.petrov.vitaliy.caraccidentapp.presentation.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R

class ProfileInfoRecyclerViewAdapter(
    private val values: ArrayList<String>,
    private val profileInfoFragment: ProfileInfoFragment
) : RecyclerView.Adapter<ProfileInfoRecyclerViewAdapter.ViewHolder>() {

    private val holders = ArrayList<ProfileInfoRecyclerViewAdapter.ViewHolder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_info_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            1 -> {
                setProfileInfo(holder, values[0], values[1], values[2])
            }
            2 -> {
                setVehicleInfo(holder, values[3], values[4], values[5], values[6], values[7], values[8])
            }
            3 -> {
                setVehicleInsurance(holder, values[9], values[10], values[11])
            }
            4 -> {
                setDriverLicense(holder, values[12], values[13], values[14])
            }
        }
        holders.add(holder)
    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val profileChapterMainInfo: TextView = view.findViewById(R.id.profile_chapter_main_info)
        val gearIcon: ImageView = view.findViewById(R.id.gear_icon_1)
        val field1: TextView = view.findViewById(R.id.field_1)
        val value1: TextView = view.findViewById(R.id.value_1)
        val field2: TextView = view.findViewById(R.id.field_2)
        val value2: TextView = view.findViewById(R.id.value_2)
        val field3: TextView = view.findViewById(R.id.field_3)
        val value3: TextView = view.findViewById(R.id.value_3)
        val field4: TextView = view.findViewById(R.id.field_4)
        val value4: TextView = view.findViewById(R.id.value_4)
        val field5: TextView = view.findViewById(R.id.field_5)
        val value5: TextView = view.findViewById(R.id.value_5)
        val field6: TextView = view.findViewById(R.id.field_6)
        val value6: TextView = view.findViewById(R.id.value_6)
        val field7: TextView = view.findViewById(R.id.field_7)
        val value7: TextView = view.findViewById(R.id.value_7)
        val field8: TextView = view.findViewById(R.id.field_8)
        val value8: TextView = view.findViewById(R.id.value_8)
        val field9: TextView = view.findViewById(R.id.field_9)
        val value9: TextView = view.findViewById(R.id.value_9)
        val field10: TextView = view.findViewById(R.id.field_10)
        val value10: TextView = view.findViewById(R.id.value_10)
    }

    private fun setProfileInfo(
        holder: ViewHolder,
        fullName: String,
        dateOfBirth: String,
        residentialAddress: String
    ) {
        holder.profileChapterMainInfo.text = "Основная информация"
        holder.gearIcon.setOnClickListener {
            val intent =
                Intent(profileInfoFragment.context, ProfileGeneralInfoChangeActivity::class.java)
            profileInfoFragment.startActivity(intent)
        }
        holder.field1.text = "ФИО"
        holder.value1.text = fullName
        holder.field2.text = "Дата рождения"
        holder.value2.text = dateOfBirth
        holder.field3.text = "Адрес регистрации"
        holder.value3.text = residentialAddress
        holder.field4.visibility = View.GONE
        holder.value4.visibility = View.GONE
        holder.field5.visibility = View.GONE
        holder.value5.visibility = View.GONE
        holder.field6.visibility = View.GONE
        holder.value6.visibility = View.GONE
        holder.field7.visibility = View.GONE
        holder.value7.visibility = View.GONE
        holder.field8.visibility = View.GONE
        holder.value8.visibility = View.GONE
        holder.field9.visibility = View.GONE
        holder.value9.visibility = View.GONE
        holder.field10.visibility = View.GONE
        holder.value10.visibility = View.GONE
    }

    private fun setVehicleInfo(
        holder: ViewHolder, vehicleBrand: String,
        vehicleVIN: String,
        vehicleRegistrationSign: String,
        vehicleRegistrationCertificate: String,
        vehicleOwnerFullName: String,
        vehicleOwnerResidentialAddress: String
    ) {
        holder.profileChapterMainInfo.text = "Транспортное средство"
        holder.gearIcon.setOnClickListener {
            val intent =
                Intent(profileInfoFragment.context, ProfileVehicleInfoChangeActivity::class.java)
            profileInfoFragment.startActivity(intent)
        }
        holder.field1.text = "Марка ТС"
        holder.value1.text = vehicleBrand
        holder.field2.text = "Идентификационный номер"
        holder.value2.text = vehicleVIN
        holder.field3.text = "Госуд. регистр. знак"
        holder.value3.text = vehicleRegistrationSign
        holder.field4.text = "Свид. о регистрации"
        holder.value4.text = vehicleRegistrationCertificate
        holder.field5.text = "Собственник ТС"
        holder.value5.text = vehicleOwnerFullName
        holder.field6.text = "Адрес регистрации собственника ТС"
        holder.value6.text = vehicleOwnerResidentialAddress
        holder.field7.visibility = View.GONE
        holder.value7.visibility = View.GONE
        holder.field8.visibility = View.GONE
        holder.value8.visibility = View.GONE
        holder.field9.visibility = View.GONE
        holder.value9.visibility = View.GONE
        holder.field10.visibility = View.GONE
        holder.value10.visibility = View.GONE
    }

    private fun setVehicleInsurance(holder: ViewHolder, vehicleInsuranceCompany: String, vehicleInsurancePolicyNumber: String, vehicleInsurancePolicyExpirationDate: String) {
        holder.profileChapterMainInfo.text = "Страховое свидетельство"
        holder.gearIcon.setOnClickListener {
            val intent =
                Intent(profileInfoFragment.context, ProfileVehicleInsuranceChangeActivity::class.java)
            profileInfoFragment.startActivity(intent)
        }
        holder.field1.text = "Страховщик ТС"
        holder.value1.text = vehicleInsuranceCompany
        holder.field2.text = "Страховой полис"
        holder.value2.text = vehicleInsurancePolicyNumber
        holder.field3.text = "Действителен до"
        holder.value3.text = vehicleInsurancePolicyExpirationDate
        holder.field4.visibility = View.GONE
        holder.value4.visibility = View.GONE
        holder.field5.visibility = View.GONE
        holder.value5.visibility = View.GONE
        holder.field6.visibility = View.GONE
        holder.value6.visibility = View.GONE
        holder.field7.visibility = View.GONE
        holder.value7.visibility = View.GONE
        holder.field8.visibility = View.GONE
        holder.value8.visibility = View.GONE
        holder.field9.visibility = View.GONE
        holder.value9.visibility = View.GONE
        holder.field10.visibility = View.GONE
        holder.value10.visibility = View.GONE
    }

    private fun setDriverLicense(holder: ViewHolder, driverLicenseNumber: String, driverLicenseCategory: String, driverLicenseDateOfIssue: String) {
        holder.profileChapterMainInfo.text = "Водительское удостоверение"
        holder.gearIcon.setOnClickListener {
            val intent =
                Intent(profileInfoFragment.context, ProfileVehicleInsuranceChangeActivity::class.java)
            profileInfoFragment.startActivity(intent)
        }
        holder.field1.text = "Номер удостоверения"
        holder.value1.text = driverLicenseNumber
        holder.field2.text = "Категория"
        holder.value2.text = driverLicenseCategory
        holder.field3.text = "Дата выдачи"
        holder.value3.text = driverLicenseDateOfIssue
        holder.field4.visibility = View.GONE
        holder.value4.visibility = View.GONE
        holder.field5.visibility = View.GONE
        holder.value5.visibility = View.GONE
        holder.field6.visibility = View.GONE
        holder.value6.visibility = View.GONE
        holder.field7.visibility = View.GONE
        holder.value7.visibility = View.GONE
        holder.field8.visibility = View.GONE
        holder.value8.visibility = View.GONE
        holder.field9.visibility = View.GONE
        holder.value9.visibility = View.GONE
        holder.field10.visibility = View.GONE
        holder.value10.visibility = View.GONE
    }
}