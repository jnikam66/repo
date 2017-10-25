package entities;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inventory")
@SuppressWarnings("unused")
public class InventoryItem implements Serializable{
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Id
        @Column(name="itemid")
        private String itemid;

        @Column(name="inventoryid")
        private String inventoryid;

        @Column(name="itemname")
        private String itemname;

        @Column(name="Description")
        private String description;

        @Column(name="itemgroup")
        private String itemgroup;

        @Column(name="quantity")
        private String quantity;

        @Column(name="Location")
        private String Location;

        @Column(name="unitofmeasure")
        private String unitofmeasure;

        @Column(name="costperunit")
        private String costperunit;

        @Column(name="status")
        private String status;

        @Column(name="createddate")
        private String createddate;

        @Column(name="Expirationdate")
        private String Expirationdate;

        @Column(name="image")
        private Blob image;

        public String getItemid() {
                return itemid;
        }

        public void setItemid(String itemid) {
                this.itemid = itemid;
        }

        public String getInventoryid() {
                return inventoryid;
        }

        public void setInventoryid(String inventoryid) {
                this.inventoryid = inventoryid;
        }

        public String getItemname() {
                return itemname;
        }

        public void setItemname(String itemname) {
                this.itemname = itemname;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getItemgroup() {
                return itemgroup;
        }

        public void setItemgroup(String itemgroup) {
                this.itemgroup = itemgroup;
        }

        public String getQuantity() {
                return quantity;
        }

        public void setQuantity(String quantity) {
                this.quantity = quantity;
        }

        public String getLocation() {
                return Location;
        }

        public void setLocation(String location) {
                Location = location;
        }

        public String getUnitofmeasure() {
                return unitofmeasure;
        }

        public void setUnitofmeasure(String unitofmeasure) {
                this.unitofmeasure = unitofmeasure;
        }

        public String getCostperunit() {
                return costperunit;
        }

        public void setCostperunit(String costperunit) {
                this.costperunit = costperunit;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public String getCreateddate() {
                return createddate;
        }

        public void setCreateddate(String createddate) {
                this.createddate = createddate;
        }

        public String getExpirationdate() {
                return Expirationdate;
        }

        public void setExpirationdate(String expirationdate) {
                Expirationdate = expirationdate;
        }

        public Blob getImage() {
                return image;
        }

        public void setImage(Blob image) {
                this.image = image;
        }

}