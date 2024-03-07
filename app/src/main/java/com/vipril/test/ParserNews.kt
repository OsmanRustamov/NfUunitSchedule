    package com.vipril.test

    import org.jsoup.Jsoup
    import org.jsoup.nodes.Document


    fun main() {
        val url = "https://nfuunit.ru/news"
        println(parseNews(url))
    }

    fun parseNews(url: String): List<Map<String, String>> {
        val newsList = mutableListOf<Map<String, String>>()

        try {
            val doc: Document = Jsoup.connect(url).get()
            val newsItems = doc.select("div.news-item")

            for (newsItem in newsItems) {
                val temp = mutableMapOf<String, String>()

                val title = newsItem.select("header a").text()
                val description = newsItem.select("div.description").text()
                val date = newsItem.select("footer.t-right span.date").text()

                temp["title"] = title
                temp["description"] = description
                temp["date"] = date
                newsList.add(temp)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return newsList
    }
