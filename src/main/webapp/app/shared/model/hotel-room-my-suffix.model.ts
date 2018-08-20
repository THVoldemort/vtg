export interface IHotelRoomMySuffix {
  id?: number;
  type?: number;
  introduction?: string;
  numOfAdult?: number;
  numOfChild?: number;
  priceEst?: number;
  priceAct?: number;
  quantity?: number;
  filePath1?: string;
  filePath2?: string;
  filePath3?: string;
  filePath4?: string;
  filePath5?: string;
}

export const defaultValue: Readonly<IHotelRoomMySuffix> = {};