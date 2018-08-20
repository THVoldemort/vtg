export interface IPlaceMySuffix {
  id?: number;
  name?: string;
  slogan?: string;
  address?: string;
  rank?: number;
  commentId?: number;
  filePath1?: string;
  filePath2?: string;
  filePath3?: string;
  filePath4?: string;
  filePath5?: string;
  introduction?: string;
  priceFrom?: number;
  priceTo?: number;
  latitude?: number;
  longitude?: number;
  provinceId?: number;
}

export const defaultValue: Readonly<IPlaceMySuffix> = {};
