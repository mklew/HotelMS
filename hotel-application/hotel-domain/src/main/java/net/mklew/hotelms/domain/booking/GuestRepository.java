package net.mklew.hotelms.domain.booking;

import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 7:49 PM
 */
public interface GuestRepository
{
   public Collection<Guest> findAllWhereCommonNameLike(String commonName);
}
