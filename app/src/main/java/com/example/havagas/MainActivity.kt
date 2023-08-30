package com.example.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        with (activityMainBinding) {
            cellphoneCheckbox.setOnClickListener {
                if (cellphoneCheckbox.isChecked) {
                    cellphoneLinearLayout.visibility = View.VISIBLE
                } else {
                    cellphoneLinearLayout.visibility = View.GONE
                }
            }

            var aux = 0

            qualificationSpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected (
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    aux = position
                    if (position == 0 || position == 1) {
                        qualificationLinerLayout.visibility = android.view.View.VISIBLE
                        qualificationFundMedLinerLayout.visibility = android.view.View.VISIBLE
                        qualificationGradLinerLayout.visibility = android.view.View.GONE
                        qualificationDotLinearLayout.visibility = android.view.View.GONE

                    } else if (position == 2 || position == 3) {
                        qualificationLinerLayout.visibility = android.view.View.VISIBLE
                        qualificationFundMedLinerLayout.visibility = android.view.View.GONE
                        qualificationGradLinerLayout.visibility = android.view.View.VISIBLE
                        qualificationDotLinearLayout.visibility = android.view.View.GONE
                    } else if (position == 4 || position == 5) {
                        qualificationLinerLayout.visibility = android.view.View.VISIBLE
                        qualificationFundMedLinerLayout.visibility = android.view.View.GONE
                        qualificationGradLinerLayout.visibility = android.view.View.GONE
                        qualificationDotLinearLayout.visibility = android.view.View.VISIBLE
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
            saveButton.setOnClickListener {
                var linha = ""
                val msgEmail = if (emailCheckbox.isChecked) "Sim" else "Não"
                val typePhone = if (commercialRadioButton.isChecked) "Comercial" else "Residencial"

                linha = "$linha Nome completo: ${nameText.text} \n" +
                        "E-mail: ${emailText.text} \n" +
                        "Receber e-mail: $msgEmail \n" +
                        "Telefone: ${phoneText.text} \n" +
                        "Tipo: $typePhone \n"

                if (cellphoneCheckbox.isChecked) {
                    linha += "Celular: ${cellphoneText.text} \n"
                } else {
                    cellphoneText.setText("")
                }

                if (genderRadioGroup.checkedRadioButtonId == feminineRadioButton.id) {
                    linha += "Sexo: Feminino \n"
                } else {
                    linha += "Sexo: Masculino \n"
                }

                linha += "Data nascimento: ${birthDateText.text} \n"

                if (aux == 0 || aux == 1) {
                    linha += "Ano formação: ${graduationDateLinerLayout.text} \n"
                }

                if (aux == 2 || aux == 3) {
                    linha += "Ano formação: ${conclusionDateText.text} \n" +
                            "Instituição: ${institutionText.text} \n"
                }

                if (aux == 4 || aux == 5) {
                    linha += "Ano formação: ${conclusionDateDot.text} \n" +
                            "Instituição: ${institutionDotText.text} \n" +
                            "Titulo de monografia: ${titleText.text} \n" +
                            "Orientador: ${advisorText.text} \n"
                }

                linha += "Vagas de interesse: ${interestJobsText.text} \n"

                Toast.makeText(this@MainActivity, linha, Toast.LENGTH_SHORT).show()
            }

            cleanButton.setOnClickListener() {
                nameText.setText("")
                emailText.setText("")
                emailCheckbox.isChecked = false
                phoneText.setText("")
                commercialRadioButton.isChecked = true

                cellphoneCheckbox.isChecked = false
                cellphoneText.setText("")
                cellphoneLinearLayout.visibility = View.GONE

                feminineRadioButton.isChecked = true
                birthDateText.setText("")

                qualificationSpinner.setSelection(0)

                qualificationLinerLayout.visibility = android.view.View.VISIBLE
                qualificationFundMedLinerLayout.visibility = android.view.View.VISIBLE
                qualificationGradLinerLayout.visibility = android.view.View.GONE
                qualificationDotLinearLayout.visibility = android.view.View.GONE

                graduationDateLinerLayout.setText("")
                conclusionDateText.setText("")
                institutionText.setText("")
                conclusionDateDot.setText("")
                institutionDotText.setText("")
                titleText.setText("")
                advisorText.setText("")

                interestJobsText.setText("")
            }
        }
    }
    override fun onSaveInstanceState( outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(activityMainBinding) {
            outState.putString("name", nameText.text.toString())
            outState.putString("emailText", emailText.text.toString())
            outState.putBoolean("emailCheckbox", emailCheckbox.isChecked)

            outState.putString("phone", phoneText.text.toString())
            outState.putString("type", if (commercialRadioButton.isChecked) "Residencial" else "Comercial")

            outState.putBoolean("cellphoneCheckbox", cellphoneCheckbox.isChecked)
            outState.putInt("cellphoneLL", cellphoneLinearLayout.visibility)
            outState.putString("cellphone", cellphoneText.text.toString())

            outState.putString("gender", if (feminineRadioButton.isChecked) "Feminino" else "Masculino")

            outState.putInt("graduation", qualificationSpinner.selectedItemPosition)
            outState.putString("graduationDate", graduationDateLinerLayout.text.toString())
            outState.putString("conclusionDate", conclusionDateText.text.toString())
            outState.putString("institution", institutionText.text.toString())
            outState.putString("conclusionDateDoc", conclusionDateDot.text.toString())
            outState.putString("institutionDoc", institutionDotText.text.toString())
            outState.putString("title", titleText.text.toString())
            outState.putString("advisor", advisorText.text.toString())
            outState.putString("jobInterest", interestJobsText.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        with(activityMainBinding) {
            nameText.setText(savedInstanceState.getString("name"))
            emailText.setText(savedInstanceState.getString("emailText"))
            emailCheckbox.isChecked = savedInstanceState.getBoolean(("emailCheckbox"))

            phoneText.setText(savedInstanceState.getString("phone"))

            cellphoneCheckbox.isChecked = savedInstanceState.getBoolean("cellphoneCheckbox")
            cellphoneLinearLayout.visibility = savedInstanceState.getInt("cellphoneLL")
            cellphoneText.setText(savedInstanceState.getString("cellphone"))

            if (savedInstanceState.getBoolean("gender")) feminineRadioButton.isChecked else maleRadioButton.isChecked

            qualificationSpinner.setSelection(savedInstanceState.getInt("graduation"))
            graduationDateLinerLayout.setText(savedInstanceState.getString("graduationDate"))
            conclusionDateText.setText(savedInstanceState.getString("conclusionDate"))
            institutionText.setText(savedInstanceState.getString("institution"))
            conclusionDateDot.setText(savedInstanceState.getString("conclusionDateDoc"))
            institutionDotText.setText(savedInstanceState.getString("institutionDoc"))
            titleText.setText(savedInstanceState.getString("title"))
            advisorText.setText(savedInstanceState.getString("advisor"))
            interestJobsText.setText(savedInstanceState.getString("jobInterest"))
        }
    }
}