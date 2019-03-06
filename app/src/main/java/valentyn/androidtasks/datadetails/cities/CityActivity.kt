package valentyn.androidtasks.datadetails.cities

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_city.*
import valentyn.androidtasks.datadetails.DataDetailPresenter
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import android.support.design.widget.TextInputLayout
import valentyn.androidtasks.datadetails.DataDetailContract
import valentyn.androidtasks.utils.ErrorTypeTextValidate
import valentyn.androidtasks.utils.FileUtils

class CityActivity : AppCompatActivity(), DataDetailContract.View {

    private var presenter: DataDetailPresenter =
        DataDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        setSupportActionBar(toolbar_city)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
        presenter.onAttach(this, intent.getStringExtra(CITY_KEY), CITY_KEY)

        city_title_edit.addTextChangedListener(presenter.getTextValidator(city_title_input))
        city_site_edit.addTextChangedListener(presenter.getTextValidator(city_site_input))
        city_description_edit.addTextChangedListener(presenter.getTextValidator(city_description_input))
        city_country_edit.addTextChangedListener(presenter.getTextValidator(city_country_input))

        сity_image.setOnClickListener {presenter.getPhoto()}
        city_select_button.setOnClickListener { presenter.selectData() }
        city_save_button.setOnClickListener {
            presenter.saveData(
                name = city_title_edit.text.toString(),
                url = city_uri.text.toString(),
                description = city_description_edit.text.toString(),
                country = city_country_edit.text.toString(),
                site = city_site_edit.text.toString(),
                select = city_select_button.text.toString() == getString(R.string.button_selected)
            )
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.start()

    }

    override fun setTextSelectedButton(value: Int) {
        city_select_button.setText(value)
    }

    override fun setColorSelectedButton(value: Int) {
        city_select_button.setTextColor(value)
    }

    override fun setSiteText(site: String?) {
        city_site_edit.setText(site)
    }

    override fun setNameText(name: String?) {
        city_title_edit.setText(name)
    }

    override fun setDescriptionText(description: String?) {
        city_description_edit.setText(description)
    }

    override fun setCountryText(country: String?) {
        city_country_edit.setText(country)
    }

    override fun getPhotoURI(): Uri? = FileUtils.getPhotoURI(this)

    override fun getPhotoIntent(uri: Uri?){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(packageManager) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(takePictureIntent, presenter.REQUEST_TAKE_PHOTO)
        }
    }

    override fun setPhoto(uri: Uri?) {
        сity_image.setImageURI(uri)
        city_uri.text = uri.toString()
    }

    override fun loadPhoto(url: String?) {

        if (!url.isNullOrEmpty()) {
            city_uri.text = url
            Picasso.get()
                .load(url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(сity_image)
        }
    }

    override fun changeTextAndColorSelect() {
        if (city_select_button.text == getString(R.string.button_selected)) {
            city_select_button.setText(R.string.button_unselected)
            city_select_button.setTextColor(Color.GRAY)
        } else {
            city_select_button.setText(R.string.button_selected)
            city_select_button.setTextColor(Color.GREEN)
        }
    }

    override fun showEmptyError() {
        showValidateError(city_title_input, ErrorTypeTextValidate.ERROR_TITLE_EMPTY)
    }

    override fun showValidateError(textView: TextInputLayout, errorType: ErrorTypeTextValidate?) {
        textView.error = getTextError(errorType)
    }

    private fun getTextError(errorType: ErrorTypeTextValidate?): CharSequence? {
        return when (errorType) {
            ErrorTypeTextValidate.ERROR_TITLE_EMPTY -> getString(R.string.Error_title_empty)
            ErrorTypeTextValidate.ERROR_FORBIDDEN_CHARACTER -> getString(R.string.Error_forbiden_character)
            ErrorTypeTextValidate.ERROR_MAX_LENGTH -> getString(R.string.Error_max_length)
            else -> null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_city_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_share -> {
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
           presenter.result(requestCode, resultCode)
    }

    override fun onFinish() {
        finish()
    }

    override fun finish() {
        if (presenter.isDataChange) setResult(Activity.RESULT_OK)
        super.finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"
    }

}