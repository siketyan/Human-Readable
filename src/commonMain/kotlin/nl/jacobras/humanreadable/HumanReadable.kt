package nl.jacobras.humanreadable

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.math.roundToInt
import kotlin.time.Duration

object HumanReadable {

    /**
     * Returns the difference between now and [instant], in human-readable format. Also supports
     * instants in the future. For example: an instant that's 5 hours ago will return "5 hours ago".
     *
     * @param instant The [Instant] to format.
     * @return a formatted string
     */
    fun timeAgo(instant: Instant): String {
        val now = Clock.System.now()
        val diff = now - instant
        val secondsAgo = diff.inWholeSeconds

        return when {
            secondsAgo < 0 -> {
                "in ${duration(diff.absoluteValue)}"
            }
            secondsAgo <= 1 -> {
                "now"
            }
            else -> "${duration(diff)} ago"
        }
    }

    /**
     * Returns the given [duration] in human-readable format.
     * For example: a duration of 3 seconds returns "3 seconds".
     *
     * @param duration The [Duration] to format.
     * @return a formatted string
     */
    fun duration(duration: Duration): String {
        val secondsAgo = duration.inWholeSeconds
        val daysAgo = duration.inWholeDays
        val weeksAgo = duration.inWholeDays / 7f
        val monthsAgo = duration.inWholeDays / 30.5f
        val yearsAgo = duration.inWholeDays / 365

        return when {
            secondsAgo < 60 -> {
                "$secondsAgo seconds"
            }
            secondsAgo < 3600 -> {
                "${duration.inWholeMinutes} minutes"
            }
            daysAgo < 1 -> {
                "${duration.inWholeHours} hours"
            }
            daysAgo < 7 -> {
                "$daysAgo days"
            }
            daysAgo < 30 -> {
                "${weeksAgo.roundToInt()} weeks"
            }
            monthsAgo < 12 -> {
                "${monthsAgo.roundToInt()} months"
            }
            else -> {
                "$yearsAgo years"
            }
        }
    }
}