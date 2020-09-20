package com.zukron.jikanime.network

import com.zukron.jikanime.model.*
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/2/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
interface ApiService {
    @GET("top/anime/{page}/{subType}")
    fun getTopAnime(
            @Path("page") page: Int,
            @Path("subType") subType: String
    ): Flowable<TopAnimeResponse>

    @GET("season/{year}/{season}")
    fun getSeasonalAnime(
            @Path("year") year: String,
            @Path("season") season: String
    ): Flowable<SeasonalAnimeResponse>

    @GET("genre/anime/{genreId}/{page}")
    fun getGenreAnime(
            @Path("genreId") genreId: String,
            @Path("page") page: Int
    ): Flowable<GenreAnimeResponse>

    @GET("top/manga/{page}/{subType}")
    fun getTopManga(
            @Path("page") page: Int,
            @Path("subType") subType: String
    ): Flowable<TopMangaResponse>

    @GET("genre/manga/{genreId}/{page}")
    fun getGenreManga(
            @Path("genreId") genreId: String,
            @Path("page") page: Int
    ): Flowable<GenreMangaResponse>

    @GET("anime/{malId}")
    fun getDetailAnime(
            @Path("malId") malId: Int
    ): Flowable<DetailAnimeResponse>

    @GET("manga/{malId}")
    fun getDetailManga(
            @Path("malId") malId: Int
    ): Flowable<DetailMangaResponse>

    @GET("anime/{malId}/characters_staff")
    fun getCharacterAnime(
            @Path("malId") malId: Int
    ): Flowable<CharacterAnimeResponse>

    @GET("anime/{malId}/episodes/{page}")
    fun getEpisodeAnime(
            @Path("malId") malId: Int,
            @Path("page") page: Int
    ): Flowable<EpisodeAnimeResponse>

    @GET("character/{malId}")
    fun getDetailCharacter(
            @Path("malId") malId: Int
    ): Flowable<DetailCharacterResponse>

    @GET("search/anime")
    fun getSearchAnime(
            @Query("q") keyword: String,
            @Query("page") page: Int
    ): Flowable<SearchAnimeResponse>

    @GET("search/manga")
    fun getSearchManga(
            @Query("q") keyword: String,
            @Query("page") page: Int
    ): Flowable<SearchMangaResponse>
}