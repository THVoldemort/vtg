export const enum HotelType {
  KHACH_SAN = 'KHACH_SAN',
  NHA_NGHI = 'NHA_NGHI',
  HOTEL = 'HOTEL',
  HOMESTAY = 'HOMESTAY',
  CAN_HO = 'CAN_HO',
  RESOURCE = 'RESOURCE'
}

export interface IHotelMySuffix {
  id?: number;
  name?: string;
  starRank?: number;
  slogan?: string;
  address?: string;
  priceFrom?: number;
  priceTo?: number;
  viewCount?: number;
  convenient?: number;
  introduction?: string;
  confirmBookingType?: number;
  ratingId?: number;
  latitude?: number;
  longitude?: number;
  provinceId?: number;
  hotelType?: HotelType;
  filePath1?: string;
  filePath2?: string;
  filePath3?: string;
  filePath4?: string;
  filePath5?: string;
  placeId?: number;
}

export const defaultValue: Readonly<IHotelMySuffix> = {};
