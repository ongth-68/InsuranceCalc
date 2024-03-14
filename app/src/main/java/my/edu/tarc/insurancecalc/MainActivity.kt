package my.edu.tarc.insurancecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.insurancecalc.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val premium = arrayOf(60, 70, 90, 120, 150, 150)
        val maleExtra = arrayOf(0, 50, 100, 150, 200, 200)
        val smokerExtra = arrayOf(0, 100, 150, 200, 250, 300)

        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val symbol = numberFormat.currency?.symbol

        binding.buttonCalculate.setOnClickListener {
            val basic = premium[binding.spinAge.selectedItemPosition]

            val male =
                when(binding.rgGender.checkedRadioButtonId) {
                    R.id.rbMale->maleExtra[binding.spinAge.selectedItemPosition]
                    R.id.rbFemale->0
                    else->0
                }

            val smoker = if (binding.cbSmoker.isChecked) smokerExtra[binding.spinAge.selectedItemPosition] else 0

            binding.tvBasicPrice.text = String.format("%s %d", symbol, basic)
            binding.tvMalePrice.text = String.format("%s %d", symbol, male)
            binding.tvSmokerPrice.text = String.format("%s %d", symbol, smoker)
            binding.tvTotalPrice.text = String.format("%s %d", symbol, basic + male + smoker)
        }

        binding.buttonReset.setOnClickListener {
            binding.spinAge.setSelection(0)
            binding.rgGender.check(R.id.rbMale)
            binding.cbSmoker.isChecked = false

            binding.tvBasicPrice.text = ""
            binding.tvMalePrice.text = ""
            binding.tvSmokerPrice.text = ""
            binding.tvTotalPrice.text = ""
        }
    }
}