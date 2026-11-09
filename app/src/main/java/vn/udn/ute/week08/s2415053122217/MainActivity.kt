package vn.udn.ute.week08.s2415053122217

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var etCourseName:TextInputEditText
    private lateinit var etQT:TextInputEditText
    private lateinit var etCK: TextInputEditText
    private lateinit var btnCheck: MaterialButton
    private lateinit var btnClear: MaterialButton
    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        etCourseName=findViewById(R.id.etCourseName)
        etQT=findViewById(R.id.etQT)
        etCK=findViewById(R.id.etCK)
        btnCheck=findViewById(R.id.btnCheck)
        btnClear=findViewById(R.id.btnClear)
        tvResult=findViewById(R.id.tvResult)
        btnCheck.setOnClickListener {
            handleCheckValidationAndCalculation()
        }
        btnClear.setOnClickListener {
            etCourseName.text?.clear()
            etCourseName.error=null
            etQT.text?.clear()
            etQT.error=null
            etCK.text?.clear()
            etCK.error=null
            tvResult.text="Kết quả sẽ hiểnt hị ở đây..."

        }

        }
    private fun handleCheckValidationAndCalculatioṇ(){
        val courseName=etCourseName.text.toString().trim()
        val qtStr=etQT.text.toString().trim()
        val ckStr=etCK.text.toString().trim()
        etCourseName.error=null
        etQT.error=null
        etCK.error=null
        var hasError=false
        if(courseName.isEmpty()||courseName.length>40)   {
        etCourseName.error="Tên học phần phải từ 1 đến 40 ký tự"
        hasError=true
        }
        val qt=qtStr.toDoubleOrNull()
        if(qt==null||qt<0.0||qt>10.0){
            etCK.error="Điểm CK phải là số  thực"
            hasError=true}
        if(hasError)
            {
            tvResult.text="Dữ liệu nhập không hợp lệ"
                return
        }
        calculateAndDisplayResult(name:String,qt:Double, ck:Double)}
    private fun calculateAndDisplayResult(name: String,qt:Double,ck:Double)
        }

    }
}