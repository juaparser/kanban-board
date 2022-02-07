package es.juaparser.kanbanboard.ui.board

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import es.juaparser.kanbanboard.R

private val TAB_TITLES = arrayOf(
    R.string.tab_board_backlog,
    R.string.tab_board_next,
    R.string.tab_board_doing,
    R.string.tab_board_done
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 4
    }
}