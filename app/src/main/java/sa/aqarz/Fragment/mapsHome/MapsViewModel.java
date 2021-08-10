package sa.aqarz.Fragment.mapsHome;


import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import sa.aqarz.Modules.CityLocation;
import sa.aqarz.Modules.RegionModules;

public class MapsViewModel extends ViewModel {

    public static MutableLiveData<List<RegionModules>> mutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<List<CityLocation>> mutableLiveData_city = new MutableLiveData<>();


    MapsRepository mapsRepository;

//    public MainViewModel() {
//        videoRepository = new VideoRepository();
//    }

    public void getRegoins(Activity activity) {
        mapsRepository = new MapsRepository(activity,true);
        mapsRepository.getOpration();
    }
    public void getRegoins_without(Activity activity) {
        mapsRepository = new MapsRepository(activity,true);
        mapsRepository.getOpration2();
    }

    public void getCity(Activity activity,String state_id,String type) {
        mapsRepository = new MapsRepository(activity,true);
        mapsRepository.getCity( state_id,type);
    }
    public void getCity2(Activity activity,String state_id) {
        mapsRepository = new MapsRepository(activity,true);
        mapsRepository.getCity2( state_id);
    }
    public void getEstate_map(Activity activity,final String requestType, String url) {
        mapsRepository = new MapsRepository(activity,true);
        mapsRepository.getEstatMaps( requestType,url);
    }
    public void getEstate_list(Activity activity,final String requestType, String url) {
        mapsRepository = new MapsRepository(activity,true);
        mapsRepository.getEstatList( requestType,url);
    } public void getEstate_list_without_loading(Activity activity,final String requestType, String url) {
        mapsRepository = new MapsRepository(activity,false);
        mapsRepository.getEstatList( requestType,url);
    }


    public LiveData<List<RegionModules>> getRegions_list() {
        return mutableLiveData;
    }

    public LiveData<List<CityLocation>> getCity_list() {
        return mutableLiveData_city;
    }


}
