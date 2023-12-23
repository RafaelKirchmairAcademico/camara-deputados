package br.ifsul.deputados.utils.api;

import java.util.List;

import br.ifsul.deputados.utils.api.domain.APIResponse;
import br.ifsul.deputados.utils.api.domain.Deputado;
import br.ifsul.deputados.utils.api.domain.Partido;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface APIService {

    String BASE_URL = "https://dadosabertos.camara.leg.br/api/v2/";

    @GET("partidos/{id}")
    Call<APIResponse<Partido>> getPartido(@Path("id") int id);

    @GET("partidos?dataInicio=2023-01-01&dataFim=2023-12-31")
    Call<APIResponse<List<Partido>>> getAllPartidos();

    @GET("partidos/{partidoId}/membros?ordenarPor=nome&ordem=ASC")
    Call<APIResponse<List<Deputado>>> getDeputadosByPartido(@Path("partidoId") int partidoId);

    @GET("deputados/{id}")
    Call<APIResponse<Deputado>> getDeputado(@Path("id") int id);

}
