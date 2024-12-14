package com.rpsouza.nearbyapp.core.network

import com.rpsouza.nearbyapp.data.model.category.Category
import com.rpsouza.nearbyapp.core.network.KtorHttpClient.httpClientAndroid
import com.rpsouza.nearbyapp.data.model.coupon.Coupon
import com.rpsouza.nearbyapp.data.model.market.Market
import com.rpsouza.nearbyapp.data.model.market.MarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object NearbyRemoteDataSource {
    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
    private const val LOCAL_HOST_PHYSICAL_BASE_URL = "http://192.168.100.54:3333"
    private const val BASE_URL = LOCAL_HOST_PHYSICAL_BASE_URL

    // 1 - Busca de categorias
    // 2 - Busca de Locais (com base em uma categoria)
    // 3 - Busca de detalhes de um local (com base em um local especifico)
    // 4 - Gerar cupom a partir de leitura do qrcode

    suspend fun getCategories(): Result<List<Category>> = try {
        val categories = httpClientAndroid.get("$BASE_URL/categories").body<List<Category>>()
        Result.success(categories)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {
        val markets =
            httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}").body<List<Market>>()
        Result.success(markets)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> = try {
        val market =
            httpClientAndroid.get("$BASE_URL/markets/${marketId}").body<MarketDetails>()
        Result.success(market)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun patchCoupon(marketId: String): Result<Coupon> = try {
        val coupon = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}").body<Coupon>()
        Result.success(coupon)
    } catch (e: Exception) {
        Result.failure(e)
    }
}