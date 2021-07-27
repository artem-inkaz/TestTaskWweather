package ui.smartpro.testtaskwweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ui.smartpro.testtaskwweather.databinding.ActivityMainBinding
import ui.smartpro.testtaskwweather.ui.FragmentJava

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar

    // создание связки, при закрытии MainActivity должны обнулят нашу ссылку _binding
    private var _binding: ActivityMainBinding? = null
    // сссылка ссылается на наш _binding данная строчка val mBinding get() = _binding!! позволит избежать проверки на null
    // _binding!! - будет 100% не null
    val mBinding get() = _binding!!

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // инициализация нашей связки
        _binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(mBinding.root)
        // инициализируем mToolbar
        mToolbar = mBinding.toolbar
        mToolbar.setTitleTextColor(R.color.color_text_status_bar)
        // установка ToolBar
        setSupportActionBar(mToolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, FragmentJava())
                .commit()
        }
}

}