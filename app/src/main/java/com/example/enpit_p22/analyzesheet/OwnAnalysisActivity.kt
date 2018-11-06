package com.example.enpit_p22.analyzesheet


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_own_analysis.*
import kotlinx.android.synthetic.main.content_own_analysis.*

class OwnAnalysisActivity : AppCompatActivity() {

    var mDatabase = FirebaseDatabase.getInstance().getReference("/AnalyzeSheet/${FirebaseAuth.getInstance().uid}")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_own_analysis)
        setSupportActionBar(toolbar)

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        Q2_spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val spinner = parent as? Spinner
                        val item = spinner?.selectedItem as? String
                        val position = spinner?.selectedItemPosition

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                }


        save_button.setOnClickListener {


            Toast.makeText(this,"保存しました。",Toast.LENGTH_LONG).show()


            writeNewQetion()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun writeNewQetion(){
        val q1:String
        val q2_1:String
        val q2_2:Int
        val q3:String
        val q4:Int
        val q5:Int
        val q6_1:Int
        val q6_2:String
        val q7:String

        q1 = if(Q1_edit_num.text == null){
            ""
        }else{
            Q1_edit_num.text.toString()
        }

        q2_1 = if (Q2_edit_num.text == null){
            ""
        }else{
            Q2_edit_num.text.toString()
        }

        q2_2 = Q2_spinner.selectedItemPosition

        q3 = if (Q3_edit_num.text == null) {
            ""
        }else {
            Q3_edit_num.text.toString()
        }

        q4 = when {
            Q4_noButtom.isChecked -> 0
            Q4_yesButtom.isChecked -> 1
            else -> 3
        }

        q5 = when {
            Q5_noButtom.isChecked -> 0
            Q5_yesButtom.isChecked -> 1
            else -> 3
        }

        q6_1 = when {
            Q6_noButtom.isChecked -> 0
            Q6_yesButtom.isChecked -> 1
            else -> 3
        }

        q6_2 = if (Q6_edit_detail.text == null){
            ""
        }else{
            Q6_edit_detail.text.toString()
        }

        q7 = if(Q7_edit_text.text == null){
            ""
        }else{
            Q7_edit_text.text.toString()
        }

        var quetion = Quetion(q1,q2_1,q2_2,q3,q4,q5,q6_1,q6_2,q7)
        mDatabase.setValue(quetion)

    }

    private fun getLatestAns(time:String){
        mDatabase.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {}

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val add_quetion = dataSnapshot.getValue<Quetion>(Quetion::class.java)
                ReadQuetion(add_quetion!!)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    fun ReadQuetion(quetion: Quetion){
        Q1_edit_num.setText(quetion.q1)
        Q2_edit_num.setText(quetion.q2_1)
        Q2_spinner.setSelection(quetion.q2_2)
        Q3_edit_num.setText(quetion.q3)
        when {
            quetion.q4 == 0 -> {
                Q4_yesButtom.isChecked = false
                Q4_noButtom.isChecked = true
            }
            quetion.q4 == 1 -> {
                Q4_yesButtom.isChecked = true
                Q4_noButtom.isChecked = false
            }
            else -> {
                Q4_yesButtom.isChecked = false
                Q4_noButtom.isChecked = false
            }
        }

        when {
            quetion.q5 == 0 -> {
                Q5_yesButtom.isChecked = false
                Q5_noButtom.isChecked = true
            }
            quetion.q5 == 1 -> {
                Q5_yesButtom.isChecked = true
                Q5_noButtom.isChecked = false
            }
            else -> {
                Q5_yesButtom.isChecked = false
                Q5_noButtom.isChecked = false
            }
        }

        when {
            quetion.q6_1 == 0 -> {
                Q6_yesButtom.isChecked = false
                Q6_noButtom.isChecked = true
            }
            quetion.q6_1 == 1 -> {
                Q6_yesButtom.isChecked = true
                Q6_noButtom.isChecked = false
            }
            else -> {
                Q6_yesButtom.isChecked = false
                Q6_noButtom.isChecked = false
            }
        }

        Q6_Edit_detali_text.text = quetion.q6_2
        Q7_edit_text.setText(quetion.q7)
    }
}