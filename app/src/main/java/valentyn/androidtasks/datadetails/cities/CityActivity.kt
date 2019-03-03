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
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import valentyn.androidtasks.datadetails.DataDetailContract

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

        city_select_button.setOnClickListener {
            presenter.setOnClickListenerSelectedButton(
                intent.getStringExtra(
                    CITY_KEY
                ), CITY_KEY
            )
        }

        city_name_edit.addTextChangedListener(presenter.getTextValidator(city_name_input))
        city_site_edit.addTextChangedListener(presenter.getTextValidator(city_site_input))
        city_description_edit.addTextChangedListener(presenter.getTextValidator(city_description_input))
        city_country_edit.addTextChangedListener(presenter.getTextValidator(city_country_input))
        сity_image.setOnClickListener {
            startActivityForResult(getImageIntent(),
                REQUEST_TAKE_PHOTO
            )
        }
        city_save_button.setOnClickListener{saveElement()}
    }

    override fun setTextSelectedButton(value: Int) { city_select_button.setText(value) }

    override fun setColorSelectedButton(value: Int) { city_select_button.setTextColor(value) }

    override fun setSiteText(site: String?) { city_site_edit.setText(site) }

    override fun setNameText(name: String?) { city_name_edit.setText(name) }

    override fun setDescriptionText(description: String?) { city_description_edit.setText(description) }

    override fun setCountryText(country: String?) { city_country_edit.setText(country) }

    override fun setImageUri(uri: Uri?) {
        сity_image.setImageURI(uri)
        city_uri.text = uri.toString()
    }

    private fun showMessage(message: String) { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }

    override fun showEmptyDataError() {
        showMessage(getString(R.string.Error_data_empty))
    }

    override fun loadPhoto(url: String?) {

        if (url != null && url.isNotEmpty()) {
            city_uri.text = url
            Picasso.get()
                .load(url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(сity_image)
        }
    }

    private fun saveElement() {
        presenter.saveElement(
            this,
            id = intent.getStringExtra(CITY_KEY),
            name = city_name_edit.text.toString(),
            url = city_uri.text.toString(),
            about = city_description_edit.text.toString(),
            country = city_country_edit.text.toString(),
            site = city_site_edit.text.toString(),
            select = city_select_button.text.toString(),
            key = CITY_KEY
        )
    }

    private fun getImageIntent(): Intent {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(packageManager) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, presenter.getPhotoURI(this))
        }
        return takePictureIntent
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

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == AppCompatActivity.RESULT_OK && presenter.photoUri != null) {
            setImageUri(presenter.photoUri)
        }
    }

    override fun onFinish() {
        finish()
    }

    override fun finish() {
        if (presenter.isChangeElement) setResult(Activity.RESULT_OK)
        super.finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"
        const val REQUEST_TAKE_PHOTO: Int = 1

    }

}