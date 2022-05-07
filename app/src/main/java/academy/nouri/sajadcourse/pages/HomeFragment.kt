package academy.nouri.sajadcourse.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.nouri.sajadcourse.R
import academy.nouri.sajadcourse.databinding.FragmentHomeBinding
import academy.nouri.sajadcourse.databinding.FragmentProfileBinding
import academy.nouri.sajadcourse.utils.STORE_USERNAME
import academy.nouri.sajadcourse.utils.showToast2
import androidx.navigation.fragment.navArgs
import nouri.`in`.goodprefslib.GoodPrefs

class HomeFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    //Other
    private var userName = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            if (GoodPrefs.getInstance().isKeyExists(STORE_USERNAME)) {
                userName = GoodPrefs.getInstance().getString(STORE_USERNAME, "")
                infoTxt.text = userName
            }
        }
    }
}