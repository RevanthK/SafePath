package com.example.revanthkorrapolu.yournotmydad.JSONSchema;

import java.util.List;

/**
 * Created by hemanth on 5/13/17.
 */

public class SpotCrimeList {


    private List<CrimesBean> crimes;

    public List<CrimesBean> getCrimes() {
        return crimes;
    }

    public void setCrimes(List<CrimesBean> crimes) {
        this.crimes = crimes;
    }

    public static class CrimesBean {
        /**
         * cdid : 92886268
         * type : Shooting
         * date : 05/12/17 03:03 PM
         * address : MARCUS GARVEY BLVD AND WILLOUGHBY AVE
         * link : https://spotcrime.com/crime/92886268-8fe0dd1a0f3a7af4f49e6f45df4eed99
         * lat : 40.694881
         * lon : -73.9403931
         */

        private int cdid;
        private String type;
        private String date;
        private String address;
        private String link;
        private double lat;
        private double lon;

        public int getCdid() {
            return cdid;
        }

        public void setCdid(int cdid) {
            this.cdid = cdid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }
}
