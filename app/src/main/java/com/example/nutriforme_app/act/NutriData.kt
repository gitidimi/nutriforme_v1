package com.example.nutriforme_app.act

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.nutriforme_app.MainActivity
import com.example.nutriforme_app.R
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL
import java.util.stream.DoubleStream.builder
import java.util.stream.IntStream.builder


private var user_field: EditText? = null
private var main_btn: Button? = null
private var btn_back: ImageView? = null
private var btn_calories: Button? = null
private var btn_new_recipe: Button? = null
private var btn_full_analysis: Button? = null
private var btn_info: Button? = null


private var result_info: TextView? = null
private var result_info_2: TextView? = null
private var result_info_3: TextView? = null
private var result_info_4: TextView? = null
private var result_info_5: TextView? = null
private var result_info_6: TextView? = null
private var result_info_7: TextView? = null
private var result_info_8: TextView? = null
private var result_info_9: TextView? = null
private var result_info_10: TextView? = null
private var result_info_11: TextView? = null
private var result_info_12: TextView? = null
private var result_info_13: TextView? = null
private var result_info_14: TextView? = null
private var result_info_15: TextView? = null
private var result_info_16: TextView? = null
private var result_info_17: TextView? = null
private var result_info_18: TextView? = null
private var result_info_19: TextView? = null
private var result_info_20: TextView? = null
private var result_info_21: TextView? = null
private var result_info_22: TextView? = null
private var result_info_23: TextView? = null
private var result_info_24: TextView? = null
private var result_info_25: TextView? = null
private var result_info_26: TextView? = null

class NutriData : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutri_data)

        user_field = findViewById(R.id.input_ingredients)
        main_btn = findViewById(R.id.btn_short_info)
        btn_info = findViewById(R.id.btn_info)


        btn_calories = findViewById(R.id.btn_cal)
        btn_new_recipe = findViewById(R.id.btn_reset)
        btn_full_analysis = findViewById(R.id.btn_long_info)

        result_info = findViewById(R.id.output_ingredients_Energy)
        result_info_2 = findViewById(R.id.output_ingredients_Fat)
        result_info_3 = findViewById(R.id.output_ingredients_Saturated)
        result_info_4 = findViewById(R.id.output_ingredients_Carbs)
        result_info_5 = findViewById(R.id.output_ingredients_Protein)
        result_info_6 = findViewById(R.id.output_ingredients_Cholesterol)
        result_info_7 = findViewById(R.id.output_ingredients_Sodium)
        result_info_8 = findViewById(R.id.output_ingredients_Calcium)
        result_info_9 = findViewById(R.id.output_ingredients_Magnesium)
        result_info_10 = findViewById(R.id.output_ingredients_Potassium)
        result_info_11 = findViewById(R.id.output_ingredients_Iron)
        result_info_12 = findViewById(R.id.output_ingredients_Zinc)
        result_info_13 = findViewById(R.id.output_ingredients_Phosphorus)
        result_info_14 = findViewById(R.id.output_ingredients_Vitamin_C)
        result_info_15 = findViewById(R.id.output_ingredients_Thiamin)
        result_info_16 = findViewById(R.id.output_ingredients_Riboflavin)
        result_info_17 = findViewById(R.id.output_ingredients_Niacin)
        result_info_18 = findViewById(R.id.output_ingredients_Vitamin_B6)
        result_info_19 = findViewById(R.id.output_ingredients_Vitamin_K)
        result_info_20 = findViewById(R.id.output_ingredients_Vitamin_B12)
        result_info_21 = findViewById(R.id.output_ingredients_Vitamin_D)
        result_info_22 = findViewById(R.id.output_ingredients_Fiber)
        result_info_23 = findViewById(R.id.output_ingredients_Vitamin_A)
        result_info_24 = findViewById(R.id.output_ingredients_Vitamin_E)
        result_info_25 = findViewById(R.id.output_ingredients_Folate)
        result_info_26 = findViewById(R.id.output_ingredients_Calories)
        btn_back = findViewById(R.id.btn_back)


        btn_info?.setOnClickListener {
            createDialog()
        }


        main_btn?.setOnClickListener {
            if (user_field?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this, "Please enter an ingredient", Toast.LENGTH_LONG).show()
            else {
                val ingr: String =
                    user_field?.text.toString().replace(" ", "%20").replace(",", "%2C")
                val key: String = "59298f89117331b9a5f862ddc220f0df"
                val type: String = "cooking"
                val url: String =
                    "https://api.edamam.com/api/nutrition-data?app_id=f8a08141&app_key=$key&nutrition-type=$type&ingr=$ingr"

                doAsync {
                    val apiResponse = URL(url).readText()
                    //Log.d("INFO", apiResponse)

                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("ENERC_KCAL").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("ENERC_KCAL").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("ENERC_KCAL").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("ENERC_KCAL").getString("unit")
                    /*  if (nutriDaily2.getJSONObject("VITA_RAE").getString("label").isEmpty() && nutriDaily2.getJSONObject("ENERC_KCAL").getString("quantity").isEmpty())
                          Log.d("INFO_3", "not empty")
                      else Log.d("INFO_4", nutriDaily2.getJSONObject("VITA_RAE").getString("label"))
                       Log.d("INFO_4", nutriDaily2.getJSONObject("ENERC_KCAL").getString("quantity"))*/

                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info?.text = "-"

                }
                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FAT").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FAT").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FAT").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FAT").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_2?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_2?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FASAT").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FASAT").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FASAT").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FASAT").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_3?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_3?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("CHOCDF").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("CHOCDF").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("CHOCDF").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("CHOCDF").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_4?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_4?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("PROCNT").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("PROCNT").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("PROCNT").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("PROCNT").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_5?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_5?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("CHOLE").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("CHOLE").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("CHOLE").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("CHOLE").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_6?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_6?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("NA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("NA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("NA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("NA").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_7?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_7?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("CA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("CA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("CA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("CA").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_8?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_8?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("MG").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("MG").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("MG").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("MG").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_9?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_9?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("K").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("K").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("K").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("K").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_10?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_10?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FE").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FE").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FE").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FE").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_11?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_11?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("ZN").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("ZN").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("ZN").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("ZN").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_12?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_12?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("P").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("P").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("P").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("P").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_13?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_13?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITC").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITC").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITC").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITC").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_14?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_14?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("THIA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("THIA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("THIA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("THIA").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_15?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_15?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("RIBF").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("RIBF").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("RIBF").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("RIBF").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_16?.text = "Riboflavin: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_16?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("NIA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("NIA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("NIA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("NIA").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_17?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_17?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITB6A").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITB6A").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITB6A").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITB6A").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_18?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_18?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITK1").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITK1").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITK1").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITK1").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_19?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_19?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITB12").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITB12").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITB12").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITB12").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_20?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_20?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITD").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITD").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITD").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITD").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_21?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_21?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FIBTG").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FIBTG").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FIBTG").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FIBTG").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_22?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_22?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITA_RAE").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITA_RAE").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITA_RAE").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITA_RAE").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_23?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_23?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("TOCPHA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("TOCPHA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("TOCPHA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("TOCPHA").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_24?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_24?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FOLDFE").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FOLDFE").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FOLDFE").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FOLDFE").getString("unit")


                    if (indicator.isNotEmpty() && indicator_2 >= 20)
                        result_info_25?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_25?.text = "-"

                }

               /* btn_new_recipe?.setOnClickListener {
                    result_info?.text = "Energy"
                    result_info_2?.text = "Fat"
                    result_info_3?.text = "Saturated"
                    result_info_4?.text = "Carbs"
                    result_info_5?.text = "Protein"
                    result_info_6?.text = "Cholesterol"
                    result_info_7?.text = "Sodium"
                    result_info_8?.text = "Calcium"
                    result_info_9?.text = "Magnesium"
                    result_info_10?.text = "Potassium"
                    result_info_11?.text = "Iron"
                    result_info_12?.text = "Zinc"
                    result_info_13?.text = "Phosphorus"
                    result_info_14?.text = "Vitamin C"
                    result_info_15?.text = "Thiamin (B1)"
                    result_info_16?.text = "Riboflavin (B2)"
                    result_info_17?.text = "Niacin (B3)"
                    result_info_18?.text = "Vitamin (B6)"
                    result_info_19?.text = "Vitamin K"
                    result_info_20?.text = "Vitamin B12"
                    result_info_21?.text = "Vitamin D"
                    result_info_22?.text = "Fiber"
                    result_info_23?.text = "Vitamin A"
                    result_info_24?.text = "Vitamin E"
                    result_info_25?.text = "Folate equivalent (total)"
                    result_info_26?.text = "Calories"
                }*/

                /*btn_calories?.setOnClickListener {
                    if (user_field?.text?.toString()?.trim()?.equals("")!!)
                        Toast.makeText(this, "Bitte Ingredients eingeben", Toast.LENGTH_LONG).show()
                    else {
                        val ingr: String =
                            user_field?.text.toString().replace(" ", "%20").replace(",", "%2C")
                        val key: String = "e2c8b52a70273829c264b79a78165770"
                        val type: String = "cooking"
                        val url: String =
                            "https://api.edamam.com/api/nutrition-data?app_id=ebac67b3&app_key=$key&nutrition-type=$type&ingr=$ingr"

                        doAsync {
                            val apiResponse = URL(url).readText()
                            val nutriDaily = JSONObject(apiResponse).getString("calories")
                            //val indicator = nutriDaily.getString("label")
                            //val indicator_2 = nutriDaily.getJSONObject("FOLDFE").getInt("quantity")

                            result_info_26?.text = "Calories: $nutriDaily\n"

                        }
                    }
                }*/


            }
        }



        btn_full_analysis?.setOnClickListener {
            if (user_field?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this, "Pleade enter an ingredient", Toast.LENGTH_LONG).show()
            else {
                val ingr: String =
                    user_field?.text.toString().replace(" ", "%20").replace(",", "%2C")
                val key: String = "59298f89117331b9a5f862ddc220f0df"
                val type: String = "cooking"
                val url: String =
                    "https://api.edamam.com/api/nutrition-data?app_id=f8a08141&app_key=$key&nutrition-type=$type&ingr=$ingr"

                doAsync {
                    val apiResponse = URL(url).readText()
                    //Log.d("INFO", apiResponse)

                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("ENERC_KCAL").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("ENERC_KCAL").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("ENERC_KCAL").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("ENERC_KCAL").getString("unit")
                    /*  if (nutriDaily2.getJSONObject("VITA_RAE").getString("label").isEmpty() && nutriDaily2.getJSONObject("ENERC_KCAL").getString("quantity").isEmpty())
                          Log.d("INFO_3", "not empty")
                      else Log.d("INFO_4", nutriDaily2.getJSONObject("VITA_RAE").getString("label"))
                       Log.d("INFO_4", nutriDaily2.getJSONObject("ENERC_KCAL").getString("quantity"))*/

                    if (indicator_3 >= 1)
                        result_info?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info?.text = "-"

                }
                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FAT").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FAT").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FAT").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FAT").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_2?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_2?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FASAT").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FASAT").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FASAT").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FASAT").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_3?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_3?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("CHOCDF").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("CHOCDF").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("CHOCDF").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("CHOCDF").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_4?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_4?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("PROCNT").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("PROCNT").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("PROCNT").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("PROCNT").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_5?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_5?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("CHOLE").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("CHOLE").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("CHOLE").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("CHOLE").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_6?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_6?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("NA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("NA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("NA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("NA").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_7?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_7?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("CA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("CA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("CA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("CA").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_8?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_8?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("MG").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("MG").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("MG").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("MG").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_9?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_9?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("K").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("K").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("K").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("K").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_10?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_10?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FE").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FE").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FE").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FE").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_11?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_11?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("ZN").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("ZN").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("ZN").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("ZN").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_12?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_12?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("P").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("P").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("P").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("P").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_13?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_13?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITC").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITC").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITC").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITC").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_14?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_14?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("THIA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("THIA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("THIA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("THIA").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_15?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_15?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("RIBF").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("RIBF").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("RIBF").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("RIBF").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_16?.text = "Riboflavin: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_16?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("NIA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("NIA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("NIA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("NIA").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_17?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_17?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITB6A").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITB6A").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITB6A").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITB6A").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_18?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_18?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITK1").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITK1").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITK1").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITK1").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_19?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_19?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITB12").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITB12").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITB12").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITB12").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_20?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_20?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITD").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITD").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITD").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITD").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_21?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_21?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FIBTG").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FIBTG").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FIBTG").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FIBTG").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_22?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_22?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("VITA_RAE").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("VITA_RAE").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("VITA_RAE").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("VITA_RAE").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_23?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_23?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("TOCPHA").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("TOCPHA").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("TOCPHA").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("TOCPHA").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_24?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_24?.text = "-"

                }

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getJSONObject("totalDaily")
                    val indicator = nutriDaily.getJSONObject("FOLDFE").getString("label")
                    val indicator_2 = nutriDaily.getJSONObject("FOLDFE").getInt("quantity")

                    val nutriDaily_2 = JSONObject(apiResponse).getJSONObject("totalNutrients")
                    val indicator_3 = nutriDaily_2.getJSONObject("FOLDFE").getInt("quantity")
                    val indicator_4 = nutriDaily_2.getJSONObject("FOLDFE").getString("unit")


                    if (indicator_3 >= 1)
                        result_info_25?.text = "$indicator: $indicator_2% ($indicator_3 $indicator_4)"
                    else result_info_25?.text = "-"

                }

                /* btn_new_recipe?.setOnClickListener {
                     result_info?.text = "Energy"
                     result_info_2?.text = "Fat"
                     result_info_3?.text = "Saturated"
                     result_info_4?.text = "Carbs"
                     result_info_5?.text = "Protein"
                     result_info_6?.text = "Cholesterol"
                     result_info_7?.text = "Sodium"
                     result_info_8?.text = "Calcium"
                     result_info_9?.text = "Magnesium"
                     result_info_10?.text = "Potassium"
                     result_info_11?.text = "Iron"
                     result_info_12?.text = "Zinc"
                     result_info_13?.text = "Phosphorus"
                     result_info_14?.text = "Vitamin C"
                     result_info_15?.text = "Thiamin (B1)"
                     result_info_16?.text = "Riboflavin (B2)"
                     result_info_17?.text = "Niacin (B3)"
                     result_info_18?.text = "Vitamin (B6)"
                     result_info_19?.text = "Vitamin K"
                     result_info_20?.text = "Vitamin B12"
                     result_info_21?.text = "Vitamin D"
                     result_info_22?.text = "Fiber"
                     result_info_23?.text = "Vitamin A"
                     result_info_24?.text = "Vitamin E"
                     result_info_25?.text = "Folate equivalent (total)"
                     result_info_26?.text = "Calories"
                 }*/

                /*btn_calories?.setOnClickListener {
                    if (user_field?.text?.toString()?.trim()?.equals("")!!)
                        Toast.makeText(this, "Bitte Ingredients eingeben", Toast.LENGTH_LONG).show()
                    else {
                        val ingr: String =
                            user_field?.text.toString().replace(" ", "%20").replace(",", "%2C")
                        val key: String = "e2c8b52a70273829c264b79a78165770"
                        val type: String = "cooking"
                        val url: String =
                            "https://api.edamam.com/api/nutrition-data?app_id=ebac67b3&app_key=$key&nutrition-type=$type&ingr=$ingr"

                        doAsync {
                            val apiResponse = URL(url).readText()
                            val nutriDaily = JSONObject(apiResponse).getString("calories")
                            //val indicator = nutriDaily.getString("label")
                            //val indicator_2 = nutriDaily.getJSONObject("FOLDFE").getInt("quantity")

                            result_info_26?.text = "Calories: $nutriDaily\n"

                        }
                    }
                }*/


            }
        }





        btn_back?.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

         btn_new_recipe?.setOnClickListener {
                   result_info?.text = "Energy"
                   result_info_2?.text = "Fat"
                   result_info_3?.text = "Saturated"
                   result_info_4?.text = "Carbs"
                   result_info_5?.text = "Protein"
                   result_info_6?.text = "Cholesterol"
                   result_info_7?.text = "Sodium"
                   result_info_8?.text = "Calcium"
                   result_info_9?.text = "Magnesium"
                   result_info_10?.text = "Potassium"
                   result_info_11?.text = "Iron"
                   result_info_12?.text = "Zinc"
                   result_info_13?.text = "Phosphorus"
                   result_info_14?.text = "Vitamin C"
                   result_info_15?.text = "Thiamin (B1)"
                   result_info_16?.text = "Riboflavin"
                   result_info_17?.text = "Niacin (B3)"
                   result_info_18?.text = "Vitamin (B6)"
                   result_info_19?.text = "Vitamin K"
                   result_info_20?.text = "Vitamin B12"
                   result_info_21?.text = "Vitamin D"
                   result_info_22?.text = "Fiber"
                   result_info_23?.text = "Vitamin A"
                   result_info_24?.text = "Vitamin E"
                   result_info_25?.text = "Folate equivalent (total)"
                   result_info_26?.text = "Calories"
               }

        btn_calories?.setOnClickListener {
            if (user_field?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this, "Bitte Ingredients eingeben", Toast.LENGTH_LONG).show()
            else {
                val ingr: String =
                    user_field?.text.toString().replace(" ", "%20").replace(",", "%2C")
                val key: String = "59298f89117331b9a5f862ddc220f0df"
                val type: String = "cooking"
                val url: String =
                    "https://api.edamam.com/api/nutrition-data?app_id=f8a08141&app_key=$key&nutrition-type=$type&ingr=$ingr"

                doAsync {
                    val apiResponse = URL(url).readText()
                    val nutriDaily = JSONObject(apiResponse).getString("calories")
                    //val indicator = nutriDaily.getString("label")
                    //val indicator_2 = nutriDaily.getJSONObject("FOLDFE").getInt("quantity")

                    result_info_26?.text = "Calories: $nutriDaily\n"

                }
            }
        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    private fun createDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("To get the correct result, you need to adhere to the following rules: \n" +
                " \n" +
                "Please indicate the unit first. Examples: 1 oz, 1 cup, 250 ml, 1 piece, etc.  \n" +
                " \n" +
                "Then specify the properties of the ingredients. Examples: wild, raw, white, brown, etc. \n" +
                " \n" +
                "And then specify the ingredient. Examples: rice, bread, milk, apple etc.  \n" +
                " \n" +
                "Enter an ingredient list for what you are cooking, like \"1 cup rice, 10 oz chickpeas\", etc. But it can also be one ingredient.")
        builder.setNeutralButton("All right", DialogInterface.OnClickListener {dialog, which -> dialog.dismiss() })
        builder.show()
    }


    /* override fun onClick(v: View) {
         when (v.id) {
            *//* R.id.btn_short_info -> {
                Toast.makeText(this, "Pressed_1", Toast.LENGTH_LONG).show()
            }*//*
        }
    }*/
}