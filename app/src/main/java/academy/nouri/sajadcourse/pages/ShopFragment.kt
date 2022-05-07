package academy.nouri.sajadcourse.pages

import academy.nouri.sajadcourse.R
import academy.nouri.sajadcourse.databinding.FragmentShopBinding
import academy.nouri.sajadcourse.event.EventChangeBackground
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ShopFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onChangeBg(event: EventChangeBackground) {
        binding.apply {
            shopInfoTxt.text = event.name
            root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal_200))
        }
    }

}