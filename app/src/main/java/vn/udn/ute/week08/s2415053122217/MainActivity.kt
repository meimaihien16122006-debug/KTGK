package vn.udn.ute.week08.s2415053122217

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var etCourseName: TextInputEditText
    private lateinit var etQT: TextInputEditText
    private lateinit var etCK: TextInputEditText
    private lateinit var btnCheck: MaterialButton
    private lateinit var btnClear: MaterialButton
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ View
        etCourseName = findViewById(R.id.etCourseName)
        etQT = findViewById(R.id.etQT)
        etCK = findViewById(R.id.etCK)
        btnCheck = findViewById(R.id.btnCheck)
        btnClear = findViewById(R.id.btnClear)
        tvResult = findViewById(R.id.tvResult)

        // Xử lý nút Kiểm tra
        btnCheck.setOnClickListener {
            handleCheckValidationAndCalculation()
        }

        // Xử lý nút Xóa (làm sạch toàn bộ ô và lỗi)
        btnClear.setOnClickListener {
            etCourseName.text?.clear()
            etCourseName.error = null
            etQT.text?.clear()
            etQT.error = null
            etCK.text?.clear()
            etCK.error = null
            tvResult.text = "Kết quả sẽ hiển thị ở đây..."
        }
    }

    private fun handleCheckValidationAndCalculation() {
        val courseName = etCourseName.text.toString().trim()
        val qtStr = etQT.text.toString().trim()
        val ckStr = etCK.text.toString().trim()

        // Reset lỗi cũ
        etCourseName.error = null
        etQT.error = null
        etCK.error = null

        var hasError = false

        // 1. Validate Tên học phần (1..40 ký tự)
        if (courseName.isEmpty() || courseName.length > 40) {
            etCourseName.error = "Tên học phần phải từ 1 đến 40 ký tự!"
            hasError = true
        }

        // 2. Validate Điểm QT (0..10, số thực hữu hạn)
        val qt = qtStr.toDoubleOrNull()
        if (qt == null || qt < 0.0 || qt > 10.0) {
            etQT.error = "Điểm QT phải là số thực từ 0.0 đến 10.0!"
            hasError = true
        }

        // 3. Validate Điểm CK (0..10, số thực hữu hạn)
        val ck = ckStr.toDoubleOrNull()
        if (ck == null || ck < 0.0 || ck > 10.0) {
            etCK.error = "Điểm CK phải là số thực từ 0.0 đến 10.0!"
            hasError = true
        }

        // Nếu có lỗi, dừng lại và không hiển thị kết quả cũ
        if (hasError) {
            tvResult.text = "Dữ liệu nhập không hợp lệ. Vui lòng sửa lại các ô báo lỗi."
            return
        }

        // Thực hiện tính toán khi dữ liệu hợp lệ hoàn toàn
        calculateAndDisplayResult(courseName, qt!!, ck!!)
    }

    private fun calculateAndDisplayResult(name: String, qt: Double, ck: Double) {
        // Công thức: T = 0.4 * QT + 0.6 * CK
        val t = 0.4 * qt + 0.6 * ck

        // Ngưỡng cá nhân: D = 5.5. Đạt khi T >= 5.5 và CK >= 3.0 (theo đề bài CK dưới 3 vẫn hợp lệ nhưng ảnh hưởng kết luận)
        val thresholdD = 5.5
        val status = if (t >= thresholdD && ck >= 3.0) "Đạt" else "Chưa đạt"

        // Hiển thị định dạng 2 chữ số thập phân
        val resultText = String.format(
            "Học phần: %s\nĐiểm QT: %.2f | Điểm CK: %.2f\nĐiểm tổng kết T: %.2f\nKết luận: %s",
            name, qt, ck, t, status
        )
        tvResult.text = resultText
    }
}