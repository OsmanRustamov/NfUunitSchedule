package com.vipril.test.ui.schedule;
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vipril.test.databinding.FragmentScheduleBinding
import com.vipril.test.ui.schedule.ScheduleViewModel
import com.vipril.test.parseScheduleByGroup
import com.vipril.test.parseAllSchedule
import com.vipril.test.filterSchedule

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private val url = "https://nfuunit.ru/timetable/fulltime/files/(8)-26.02.2024-02.03.2024-IS,AS,EK,YuR-(SPO).html"
    private val className = "ИС-41к_"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ScheduleViewModel =
            ViewModelProvider(this).get(ScheduleViewModel::class.java)

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonGroup: Button = binding.buttonGroup
        buttonGroup.setOnClickListener {
            val scheduleByGroup = parseScheduleByGroup(url, className)
            displayScheduleByGroup(scheduleByGroup)
        }

        val buttonAudience: Button = binding.buttonAudience
        buttonAudience.setOnClickListener {
            val allSchedule = parseAllSchedule(url)
            val scheduleByAud = filterSchedule(allSchedule, "316")
            displayScheduleByAud(scheduleByAud)
        }

        val buttonTeacher: Button = binding.buttonTeacher
        buttonTeacher.setOnClickListener {
            val allSchedule = parseAllSchedule(url)
            val scheduleTeacher = filterSchedule(allSchedule, "Егоров В.А.")
            displayScheduleByTeacher(scheduleTeacher)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayScheduleByGroup(schedule: List<Map<String, String>>) {
        val container: ViewGroup = binding.root

        // Очищаем контейнер перед добавлением новых элементов
        container.removeAllViews()

        // Проходим по всем элементам расписания
        for (scheduleItem in schedule) {
            // Создаем новый TextView для каждого элемента
            val textView = TextView(context)

            // Устанавливаем текст для TextView
            val text = "${scheduleItem["date"]} ${scheduleItem["dayOfWeek"]} - ${scheduleItem["lesson"]}"
            textView.text = text

            // Добавляем TextView в контейнер
            container.addView(textView)
        }
    }

    private fun displayScheduleByAud(schedule: List<Map<String, String>>) {
        val container: ViewGroup = binding.root

        // Очищаем контейнер перед добавлением новых элементов
        container.removeAllViews()

        // Проходим по всем элементам расписания
        for (scheduleItem in schedule) {
            // Создаем новый TextView для каждого элемента
            val textView = TextView(context)

            // Устанавливаем текст для TextView
            val text = "${scheduleItem["date"]} ${scheduleItem["dayOfWeek"]} - ${scheduleItem["lesson"]}"
            textView.text = text

            // Добавляем TextView в контейнер
            container.addView(textView)
        }
    }

    private fun displayScheduleByTeacher(schedule: List<Map<String, String>>) {
        val container: ViewGroup = binding.root

        // Очищаем контейнер перед добавлением новых элементов
        container.removeAllViews()

        // Проходим по всем элементам расписания
        for (scheduleItem in schedule) {
            // Создаем новый TextView для каждого элемента
            val textView = TextView(context)

            // Устанавливаем текст для TextView
            val text = "${scheduleItem["date"]} ${scheduleItem["dayOfWeek"]} - ${scheduleItem["lesson"]}"
            textView.text = text

            // Добавляем TextView в контейнер
            container.addView(textView)
        }
    }
}
