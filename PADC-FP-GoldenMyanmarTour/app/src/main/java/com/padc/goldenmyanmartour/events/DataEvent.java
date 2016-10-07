package com.padc.goldenmyanmartour.events;

import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/24/2016.
 */
public class DataEvent {

    public static class SuccessEvent {

        private ArrayList<PackageVO> mPackageList;

        public SuccessEvent(ArrayList<PackageVO> packageVOArrayList) {
            this.mPackageList = packageVOArrayList;
        }

        public ArrayList<PackageVO> getmPackageList() {
            return mPackageList;
        }
    }

    public static class FailedEvent {
        private String message;

        public FailedEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class DestinationDataLoaded {
        private String message;
        private List<DestinationVO> destinationVOList;

        public DestinationDataLoaded(String message, List<DestinationVO> destinationVOList) {
            this.message = message;
            this.destinationVOList = destinationVOList;
        }

        public String getMessage() {
            return message;
        }

        public List<DestinationVO> getDestinationVOList() {
            return destinationVOList;
        }
    }

    public static class PackageDataLoaded {
        private String message;
        private List<PackageVO> packageVOList;

        public PackageDataLoaded(String message, List<PackageVO> packageVOList) {
            this.message = message;
            this.packageVOList = packageVOList;
        }

        public String getMessage() {
            return message;
        }

        public List<PackageVO> getPackageVOList() {
            return packageVOList;
        }
    }

    public static class AttractionPlaceDataLoaded {
        private String message;
        private List<AttractionPlacesVO> attractionPlacesVOList;

        public AttractionPlaceDataLoaded(String message, List<AttractionPlacesVO> attractionPlacesVOList) {
            this.message = message;
            this.attractionPlacesVOList = attractionPlacesVOList;
        }

        public String getMessage() {
            return message;
        }

        public List<AttractionPlacesVO> getAttractionPlacesVOList() {
            return attractionPlacesVOList;
        }
    }


    public static class FestivalDataLoaded {
        private String message;
        private List<FestivalVO> festivalVOList;

        public FestivalDataLoaded(String message, List<FestivalVO> mFestivalVoList) {
            this.message = message;
            this.festivalVOList = mFestivalVoList;
        }

        public String getMessage() {
            return message;
        }

        public List<FestivalVO> getFestivalVOList() {
            return festivalVOList;
        }
    }

}
