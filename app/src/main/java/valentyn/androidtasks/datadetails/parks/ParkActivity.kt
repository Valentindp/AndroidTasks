package valentyn.androidtasks.datadetails.parks

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import valentyn.androidtasks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_park.*
import valentyn.androidtasks.datadetails.DataDetailContract
import valentyn.androidtasks.datadetails.DataDetailPresenter

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

        park_name_edit.addTextChangedListener(presenter.getTextValidator(park_name_input))
        park_site_edit.addTextChangedListener(presenter.getTextValidator(park_site_input))
        park_description_edit.addTextChangedListener(presenter.getTextValidator(park_description_input))
        park_country_edit.addTextChangedListener(presenter.getTextValidator(park_country_input))

        park_image.setOnClickListener {
            startActivityForResult(getImageIntent(),
                REQUEST_TAKE_PHOTO
            )
        }
        park_select_button.setOnClickListener { presenter.selectData() }
        park_save_button.setOnClickListener {
            presenter.saveData(
                name = park_name_edit.text.toString(),
                url = park_uri.text.toString(),
                description = park_description_edit.text.toString(),
                country = park_country_edit.text.toString(),
                site = park_site_edit.text.toString(),
                select = park_select_button.text.toString() == getString(R.string.button_selected)
            )
        }
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
        park_name_edit.setText(name)
    }

    override fun setDescriptionText(description: String?) {
        park_description_edit.setText(description)
    }

    override fun setCountryText(country: String?) {
        park_country_edit.setText(country)
    }

    override fun setImageUri(uri: Uri?) {
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

    override fun showEmptyDataError() {
        park_name_input.error = getString(R.string.Error_park_empty)
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

    private fun getImageIntent(): Intent {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(packageManager) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, presenter.getPhotoURI(this))
        }
        return takePictureIntent
    }

    override fun onFinish() {
        finish()
    }

    override fun finish() {
        if (presenter.isDataChange) setResult(AppCompatActivity.RESULT_OK)
        super.finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    companion object {

        const val PARK_KEY = "PARK_KEY"
        const val REQUEST_TAKE_PHOTO: Int = 1

    }
}