package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inventory")
@SuppressWarnings("unused")
public class Inventory implements Serializable{
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        @Column(name="inventoryid")
        private String inventoryId;

        @Column(name="inventoryname")
        private String inventoryName;

        @Column(name="numberofitems")
        private String numberOfItems;

        @Column(name="location")
        private String location;

        @Column(name="createdby")
        private String createdby;

        @Column(name="lastupdatedby")
        private String lastupdatedby;

        @Column(name="company")
        private String company;

        @Column(name="country")
        private String country;

        public String getInventoryId() {
                return inventoryId;
        }

        public void setInventoryId(String inventoryId) {
                this.inventoryId = inventoryId;
        }

        public String getInventoryName() {
                return inventoryName;
        }

        public void setInventoryName(String inventoryName) {
                this.inventoryName = inventoryName;
        }

        public String getNumberOfItems() {
                return numberOfItems;
        }

        public void setNumberOfItems(String numberOfItems) {
                this.numberOfItems = numberOfItems;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public String getCreatedby() {
                return createdby;
        }

        public void setCreatedby(String createdby) {
                this.createdby = createdby;
        }

        public String getLastupdatedby() {
                return lastupdatedby;
        }

        public void setLastupdatedby(String lastupdatedby) {
                this.lastupdatedby = lastupdatedby;
        }

        public String getCompany() {
                return company;
        }

        public void setCompany(String company) {
                this.company = company;
        }

        public String getCountry() {
                return country;
        }

        public void setCountry(String country) {
                this.country = country;
        }


}