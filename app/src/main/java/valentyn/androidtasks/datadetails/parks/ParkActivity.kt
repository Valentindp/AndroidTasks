package valentyn.androidtasks.datadetails.parks

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_park.*
import valentyn.androidtasks.datadetails.DataDetailContract
import valentyn.androidtasks.datadetails.DataDetailPresenter
import valentyn.androidtasks.utils.ErrorTypeTextValidate
import valentyn.androidtasks.utils.FileUtils

class ParkActivity : AppCompatActivity(), DataDetailContract.View {

    private var presenter: DataDetailPresenter =
        DataDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park)

        setSupportActionBar(toolbar_park)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        presenter.onAttach(this, intent.getStringExtra(PARK_KEY), PARK_KEY)

        park_title_edit.addTextChangedListener(presenter.getTextValidator(park_title_input))
        park_site_edit.addTextChangedListener(presenter.getTextValidator(park_site_input))
        park_description_edit.addTextChangedListener(presenter.getTextValidator(park_description_input))
        park_country_edit.addTextChangedListener(presenter.getTextValidator(park_country_input))

        park_image.setOnClickListener {presenter.getPhoto() }
        park_select_button.setOnClickListener { presenter.selectData() }
        park_save_button.setOnClickListener {
            presenter.saveData(
                name = park_title_edit.text.toString(),
                url = park_uri.text.toString(),
                description = park_description_edit.text.toString(),
                country = park_country_edit.text.toString(),
                site = park_site_edit.text.toString(),
                select = park_select_button.text.toString() == getString(R.string.button_selected)
            )
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.start()

    }

    override fun setTextSelectedButton(value: Int) {
        park_select_button.setText(value)
    }

    override fun setColorSelectedButton(value: Int) {
        park_select_button.setTextColor(value)
    }

    override fun setSiteText(site: String?) {
        park_site_edit.setText(site)
    }

    override fun setNameText(name: String?) {
        park_title_edit.setText(name)
    }

    override fun setDescriptionText(description: String?) {
        park_description_edit.setText(description)
    }

    override fun setCountryText(country: String?) {
        park_country_edit.setText(country)
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
        park_image.setImageURI(uri)
        park_uri.text = uri.toString()
    }

    override fun loadPhoto(url: String?) {

        if (!url.isNullOrEmpty()) {
            park_uri.text = url
            Picasso.get()
                .load(url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(park_image)
        }
    }

    override fun changeTextAndColorSelect() {
        if (park_select_button.text == getString(R.string.button_selected)) {
            park_select_button.setText(R.string.button_unselected)
            park_select_button.setTextColor(Color.GRAY)
        } else {
            park_select_button.setText(R.string.button_selected)
            park_select_button.setTextColor(Color.GREEN)
        }}

    override fun showEmptyError() {
        showValidateError(park_title_input, ErrorTypeTextValidate.ERROR_TITLE_EMPTY)
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
        menuInflater.inflate(R.menu.toolbar_park_menu, menu)
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
        super.finish()
        if (presenter.isDataChange) setResult(AppCompatActivity.RESULT_OK)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    companion object {
        const val PARK_KEY = "PARK_KEY"
    }
}