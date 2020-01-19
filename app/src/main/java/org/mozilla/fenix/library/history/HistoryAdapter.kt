/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.library.history

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.synthetic.main.history_list_item.view.*
import org.mozilla.fenix.R
import org.mozilla.fenix.library.SelectionHolder
import org.mozilla.fenix.library.history.viewholders.HistoryListItemViewHolder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

enum class HistoryItemTimeGroup {
    Today, Yesterday, ThisWeek, PreviousWeek, ThisMonth, PreviousMonth, Older;

    var groupVisible = true

    fun humanReadable(context: Context): String = when (this) {
        Today -> context.getString(R.string.history_today)
        Yesterday -> context.getString(R.string.history_yesterday)
        ThisWeek -> context.getString(R.string.history_this_week)
        PreviousWeek -> context.getString(R.string.history_previous_week)
        ThisMonth -> context.getString(R.string.history_this_month)
        PreviousMonth -> context.getString(R.string.history_previous_month)
        Older -> context.getString(R.string.history_older)

        //Older -> String.format("%02i - %04i", date.month,date.year)
    }
}

class HistoryAdapter(
    private val historyInteractor: HistoryInteractor
) : PagedListAdapter<HistoryItem, HistoryListItemViewHolder>(historyDiffCallback),
    SelectionHolder<HistoryItem> {

    private var mode: HistoryFragmentState.Mode = HistoryFragmentState.Mode.Normal
    override val selectedItems get() = mode.selectedItems

    override fun getItemViewType(position: Int): Int = HistoryListItemViewHolder.LAYOUT_ID

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return HistoryListItemViewHolder(view, historyInteractor, this)
    }

    fun updateMode(mode: HistoryFragmentState.Mode) {
        this.mode = mode
        // Update the delete button alpha that the first item holds
        if (itemCount > 0) notifyItemChanged(0)
    }

    override fun onBindViewHolder(holder: HistoryListItemViewHolder, position: Int) {
        val previous = if (position == 0) null else getItem(position - 1)
        val current = getItem(position) ?: return

        val previousHeader = previous?.let(::timeGroupForHistoryItem)
        val currentHeader = timeGroupForHistoryItem(current)
        val timeGroup = if (currentHeader != previousHeader) currentHeader else previousHeader

        holder.bind(
            current, timeGroup, position == 0, mode,
            this, currentHeader != previousHeader
        )
    }

    companion object {
        private fun isDateThisWeek(time: Long): Boolean {
            val today = Calendar.getInstance()
            val week = today.get(Calendar.WEEK_OF_YEAR)
            val year = today.get(Calendar.YEAR)
            val checked = Calendar.getInstance()
            checked.timeInMillis = time
            return week == checked.get(Calendar.WEEK_OF_YEAR) && year == checked.get(Calendar.YEAR)
        }

        private fun isDatePreviousWeek(time: Long): Boolean {
            val today = Calendar.getInstance()
            val week = today.get(Calendar.WEEK_OF_YEAR)
            val year = today.get(Calendar.YEAR)
            if (week == 1) {
                //FIXME
                return false
            } else {
                val checked = Calendar.getInstance()
                checked.timeInMillis = time
                return week == checked.get(Calendar.WEEK_OF_YEAR) - 1 && year == checked.get(
                    Calendar.YEAR
                )
            }
        }

        private fun isDateThisMonth(time: Long): Boolean {
            val today = Calendar.getInstance()
            val month = today.get(Calendar.MONTH)
            val year = today.get(Calendar.YEAR)
            val checked = Calendar.getInstance()
            checked.timeInMillis = time
            return month == checked.get(Calendar.MONTH) && year == checked.get(Calendar.YEAR)
        }

        private fun isDatePreviousMonth(time: Long): Boolean {
            val today = Calendar.getInstance()
            val month = today.get(Calendar.MONTH)
            val year = today.get(Calendar.YEAR)
            if (month == 0) {
                //FIXME
                return false
            } else {
                val checked = Calendar.getInstance()
                checked.timeInMillis = time
                return month == checked.get(Calendar.MONTH) - 1 && year == checked.get(Calendar.YEAR)
            }
        }

        private fun timeGroupForHistoryItem(item: HistoryItem): HistoryItemTimeGroup {
            return when {
                DateUtils.isToday(item.visitedAt) -> HistoryItemTimeGroup.Today
                DateUtils.isToday(item.visitedAt + DateUtils.DAY_IN_MILLIS) -> HistoryItemTimeGroup.Yesterday
                isDateThisWeek(item.visitedAt) -> HistoryItemTimeGroup.ThisWeek
                isDatePreviousWeek(item.visitedAt) -> HistoryItemTimeGroup.PreviousWeek
                isDateThisMonth(item.visitedAt) -> HistoryItemTimeGroup.ThisMonth
                isDatePreviousMonth(item.visitedAt) -> HistoryItemTimeGroup.PreviousMonth
                else -> HistoryItemTimeGroup.Older
            }
        }

        private val historyDiffCallback = object : DiffUtil.ItemCallback<HistoryItem>() {
            override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: HistoryItem, newItem: HistoryItem): Any? {
                return newItem
            }
        }
    }
}
