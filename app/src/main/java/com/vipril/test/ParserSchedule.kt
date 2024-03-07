package com.vipril.test

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun main() {
    val url = "https://nfuunit.ru/timetable/fulltime/files/(9)-04.03.2024-09.03.2024-IS,AS,EK,YuR-(SPO).html"
    val className = "ИС-41к_"
    val allSchedule = parseAllSchedule(url)
    val scheduleByAud = filterSchedule(allSchedule, "316")
    val scheduleByTeacher = filterSchedule(allSchedule, "Егоров В.А")
    val scheduleByGroup = parseScheduleByGroup(url, className)
    println("By group")
    for (item in scheduleByGroup) {
        println(item)
    }
    println("By aud")
    for (item in scheduleByAud) {
        println(item)
    }
    println("By teacher")
    for (item in scheduleByTeacher) {
        println(item)
    }
}

fun parseAllSchedule(url: String): List<Map<String, String>> {
    val tdAttributesList = mutableListOf<Map<String, String>>()
    try {
        val doc: Document = Jsoup.connect(url).get()
        val Schedule = doc.select("td")


        for (tdElement in Schedule) {
            val tdAttributesMap = mutableMapOf<String, String>()

            // Получаем текст тега и добавляем его в карту атрибутов
            val text = tdElement.text()
            tdAttributesMap["text"] = text

            // Получаем атрибуты тега и добавляем их в карту атрибутов
            val attributes = tdElement.attributes()
            for (attribute in attributes) {
                tdAttributesMap[attribute.key] = attribute.value
            }

            // Добавляем карту атрибутов в список
            tdAttributesList.add(tdAttributesMap)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return tdAttributesList
}

fun filterSchedule(schedule: List<Map<String, String>>, filter: String): List<Map<String, String>> {
    return schedule.filter { it["text"]?.contains(filter) ?: false }
}

fun parseScheduleByGroup(url: String, className: String): List<Map<String, String>> {
    val tdAttributesList = mutableListOf<Map<String, String>>()
    try {
        val doc: Document = Jsoup.connect(url).get()
        val ScheduleGroup = doc.select("td.$className")

        for (tdElement in ScheduleGroup) {
            val tdAttributesMap = mutableMapOf<String, String>()

            val text = tdElement.text()
            tdAttributesMap["text"] = text

            val attributes = tdElement.attributes()
            for (attribute in attributes) {
                tdAttributesMap[attribute.key] = attribute.value
            }

            tdAttributesList.add(tdAttributesMap)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return tdAttributesList
}