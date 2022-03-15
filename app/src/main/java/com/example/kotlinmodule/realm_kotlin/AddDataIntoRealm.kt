package com.example.kotlinmodule.realm_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityAddDataIntoRealmBinding
import io.realm.Realm
import io.realm.RealmConfiguration

private lateinit var binding:ActivityAddDataIntoRealmBinding
private lateinit var realm:Realm
private var cars:Cars? = null                            // lateinit does not work with null objects


class AddDataIntoRealm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Add Data in Realm Activity"
        binding= ActivityAddDataIntoRealmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Realm.init(this)
        val configuration:RealmConfiguration=RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .name(getString(R.string.app_name))
            .build()
        realm= Realm.getInstance(configuration)

        intent=intent
        cars=intent.getParcelableExtra("cars") as? Cars
        val idNumber:Int=intent.getIntExtra("id",0)

        if(cars!=null){

            binding.edtId.setText(cars!!.id.toString())
            binding.edtCarName.setText(cars!!.carName)
            binding.edtBrandName.setText(cars!!.brand)
            binding.edtCarPrice.setText(cars!!.price.toString())
        }


        binding.btnSubmitCar.setOnClickListener {

            if(cars==null){
                val id=binding.edtId.text.toString().toInt()
                val carName=binding.edtCarName.text.toString()
                val brandName=binding.edtBrandName.text.toString()
                val price=binding.edtCarPrice.text.toString().toInt()

                val cars=Cars(id,carName,brandName,price)

                realm.executeTransaction(Realm.Transaction {

                    it.insert(cars)
                    Toast.makeText(this,"Data Added SuccessFully",Toast.LENGTH_SHORT).show()
                    intent= Intent(this,RealmListActivity::class.java)
                    startActivity(intent)
                })
            }
            else {


                realm.executeTransaction(Realm.Transaction {
                    cars=it.where(Cars::class.java).equalTo("id",idNumber).findFirst()!!

                    cars!!.id=binding.edtId.text.toString().toInt()
                    cars!!.carName=binding.edtCarName.text.toString()
                    cars!!.brand=binding.edtBrandName.text.toString()
                    cars!!.price=binding.edtCarPrice.text.toString().toInt()

                    Toast.makeText(this,"Data Added SuccessFully",Toast.LENGTH_SHORT).show()
                    intent= Intent(this,RealmListActivity::class.java)
                    startActivity(intent)

                })



            }

        }
    }
}