package com.burakkocak.casestudies.employee.resource;

import java.util.Date;

/**
 * @author Burak KOCAK
 * @date 8/4/2021
 */
public interface IPrizeWinnersHistoryDto {

    Long getIdentity();

    String getName();

    String getSurname();

    Date getDate();

}
