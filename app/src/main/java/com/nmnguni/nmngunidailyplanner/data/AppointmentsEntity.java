package com.nmnguni.nmngunidailyplanner.data;

import android.content.Entity;

import java.util.Date;

public class AppointmentsEntity {
    private int appointmentId;
    private Date appointmentDate;
    private String appointmentMustDoContent;
    private String appointmentsContent;
    private boolean appointmentOptionGroceries;
    private boolean appointmentOptionGym;

    // Constructor: Default
    public AppointmentsEntity() {
    }

    // Constructor: Parameterized
    public AppointmentsEntity
    (
        int appointmentId, Date appointmentDate, String appointmentMustDoContent,
        String appointmentsContent, boolean appointmentOptionGroceries, boolean appointmentOptionGym
    ) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentMustDoContent = appointmentMustDoContent;
        this.appointmentsContent = appointmentsContent;
        this.appointmentOptionGroceries = appointmentOptionGroceries;
        this.appointmentOptionGym = appointmentOptionGym;
    }

    /*
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Entity: ").append(getEntityValues());
        for (Entity.NamedContentValues namedValue : getSubValues()) {
            sb.append("\n  ").append(namedValue.uri);
            sb.append("\n  -> ").append(namedValue.values);
        }
        return sb.toString();
    }
    */

    /**
     * Returns a string representation of the {@code AppointmentsEntity} class.
     */
    @Override
    public String toString() {
        return
            "AppointmentsEntity{" +
            "appointmentId=" + appointmentId +
            ", appointmentDate=" + appointmentDate +
            ", appointmentMustDoContent='" + appointmentMustDoContent + '\'' +
            ", appointmentsContent='" + appointmentsContent + '\'' +
            ", appointmentOptionGroceries=" + appointmentOptionGroceries +
            ", appointmentOptionGym=" + appointmentOptionGym +
            '}';
    }

    // Properties: Getters and Setters.
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentMustDoContent() {
        return appointmentMustDoContent;
    }

    public void setAppointmentMustDoContent(String appointmentMustDoContent) {
        this.appointmentMustDoContent = appointmentMustDoContent;
    }

    public String getAppointmentsContent() {
        return appointmentsContent;
    }

    public void setAppointmentsContent(String appointmentsContent) {
        this.appointmentsContent = appointmentsContent;
    }

    public boolean getAppointmentOptionGroceries() { return appointmentOptionGroceries; }

    public void setAppointmentOptionGroceries(boolean appointmentOptionGroceries) {
        this.appointmentOptionGroceries = appointmentOptionGroceries;
    }

    public boolean getAppointmentOptionGym() {
        return appointmentOptionGym;
    }

    public void setAppointmentOptionGym(boolean appointmentOptionGym) {
        this.appointmentOptionGym = appointmentOptionGym;
    }
}
