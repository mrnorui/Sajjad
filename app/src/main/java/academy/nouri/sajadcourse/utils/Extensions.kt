package academy.nouri.sajadcourse.utils

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun showToast2(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun RecyclerView.initRecycler(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = layoutManager
    this.adapter = adapter
    this.setHasFixedSize(true)
}

fun Any.moneySeparating():String{
    return DecimalFormat(",###.##").format(this)
}