package com.zukron.jikanime.model.helper

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/16/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
sealed class HomeHelper {
    // Top anime
    object TopAnime : HomeHelper() {
        val topAnimeTitles: List<String> = listOf(
                "airing", "upcoming", "tv",
                "movie", "ova", "special"
        )
    }

    // Seasonal anime
    sealed class SeasonalAnime : HomeHelper() {
        object Winter : SeasonalAnime() {
            override fun toString(): String = "winter"
        }

        object Fall : SeasonalAnime() {
            override fun toString(): String = "fall"
        }

        object Summer : SeasonalAnime() {
            override fun toString(): String = "summer"
        }

        object Spring : SeasonalAnime() {
            override fun toString(): String = "spring"
        }

        val yearList: List<String> = listOf("2016", "2017", "2018", "2019", "2020", "2021")
    }

    // Genre anime
    object GenreAnime : HomeHelper() {
        class Genre(val name: String, val id: String)

        val genreList: List<Genre> = listOf(
                Genre("Action", "1"),
                Genre("Adventure", "2"),
                Genre("Cars", "3"),
                Genre("Comedy", "4"),
                Genre("Dementia", "5"),
                Genre("Demons", "6"),
                Genre("Mystery", "7"),
                Genre("Drama", "8"),
                Genre("Ecchi", "9"),
                Genre("Fantasy", "10"),
                Genre("Game", "11"),
                Genre("Hentai", "12"),
                Genre("Historical", "13"),
                Genre("Horror", "14"),
                Genre("Kids", "15"),
                Genre("Magic", "16"),
                Genre("Martial Arts", "17"),
                Genre("Mecha", "18"),
                Genre("Music", "19"),
                Genre("Parody", "20"),
                Genre("Samurai", "21"),
                Genre("Romance", "22"),
                Genre("School", "23"),
                Genre("Sci Fi", "24"),
                Genre("Shoujo", "25"),
                Genre("Shoujo Ai", "26"),
                Genre("Shounen", "27"),
                Genre("Shounen Ai", "28"),
                Genre("Space", "29"),
                Genre("Sports", "30"),
                Genre("Super Power", "31"),
                Genre("Vampire", "32"),
                Genre("Yaoi", "33"),
                Genre("Yuri", "34"),
                Genre("Harem", "35"),
                Genre("Slice Of Life", "36"),
                Genre("Supernatural", "37"),
                Genre("Military", "38"),
                Genre("Police", "39"),
                Genre("Psychological", "40"),
                Genre("Thriller", "41"),
                Genre("Seinen", "42"),
                Genre("Josei", "43")
        )
    }

    // Top manga
    object TopManga : HomeHelper() {
        val topMangaTitles: List<String> = listOf(
                "manga", "novels", "oneshots",
                "doujin", "manhwa", "manhua"
        )
    }

    // Genre manga
    object GenreManga : HomeHelper() {
        class Genre(val name: String, val id: String)

        val genreList: List<Genre> = listOf(
                Genre("Action", "1"),
                Genre("Adventure", "2"),
                Genre("Cars", "3"),
                Genre("Comedy", "4"),
                Genre("Dementia", "5"),
                Genre("Demons", "6"),
                Genre("Mystery", "7"),
                Genre("Drama", "8"),
                Genre("Ecchi", "9"),
                Genre("Fantasy", "10"),
                Genre("Game", "11"),
                Genre("Hentai", "12"),
                Genre("Historical", "13"),
                Genre("Horror", "14"),
                Genre("Kids", "15"),
                Genre("Magic", "16"),
                Genre("Martial Arts", "17"),
                Genre("Mecha", "18"),
                Genre("Music", "19"),
                Genre("Parody", "20"),
                Genre("Samurai", "21"),
                Genre("Romance", "22"),
                Genre("School", "23"),
                Genre("Sci Fi", "24"),
                Genre("Shoujo", "25"),
                Genre("Shoujo Ai", "26"),
                Genre("Shounen", "27"),
                Genre("Shounen Ai", "28"),
                Genre("Space", "29"),
                Genre("Sports", "30"),
                Genre("Super Power", "31"),
                Genre("Vampire", "32"),
                Genre("Yaoi", "33"),
                Genre("Yuri", "34"),
                Genre("Harem", "35"),
                Genre("Slice Of Life", "36"),
                Genre("Supernatural", "37"),
                Genre("Military", "38"),
                Genre("Police", "39"),
                Genre("Psychological", "40"),
                Genre("Seinen", "41"),
                Genre("Josei", "42"),
                Genre("Doujinshi", "43"),
                Genre("Gender Bender", "44"),
                Genre("Thriller", "45")
        )
    }

    // favorite
    object Favorite : HomeHelper() {
        val favoriteTitles: List<String> = listOf(
                "anime", "manga"
        )
    }
}