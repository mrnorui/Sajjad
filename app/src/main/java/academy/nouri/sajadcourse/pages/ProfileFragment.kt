package academy.nouri.sajadcourse.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.nouri.sajadcourse.R
import academy.nouri.sajadcourse.databinding.FragmentProfileBinding
import academy.nouri.sajadcourse.event.EventChangeBackground
import academy.nouri.sajadcourse.utils.STORE_USERNAME
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import nouri.`in`.goodprefslib.GoodPrefs
import org.greenrobot.eventbus.EventBus

class ProfileFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentProfileBinding

    //Other
    private var storedUsername = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //Show
            if (GoodPrefs.getInstance().isKeyExists(STORE_USERNAME)) {
                storedUsername = GoodPrefs.getInstance().getString(STORE_USERNAME, "")
                infoTxt.text = storedUsername
            }
            //Save
            saveData.setOnClickListener {
                val userName = nameEdt.text.toString()
                GoodPrefs.getInstance().saveString(STORE_USERNAME, userName)
                infoTxt.text = userName
                //Event
                EventBus.getDefault().postSticky(EventChangeBackground(userName))
            }
            //Get
            getData.setOnClickListener {
                infoTxt.text = storedUsername
            }
        }
    }
}