import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHotelMySuffix, defaultValue } from 'app/shared/model/hotel-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_HOTEL_LIST: 'hotel/FETCH_HOTEL_LIST',
  FETCH_HOTEL: 'hotel/FETCH_HOTEL',
  CREATE_HOTEL: 'hotel/CREATE_HOTEL',
  UPDATE_HOTEL: 'hotel/UPDATE_HOTEL',
  DELETE_HOTEL: 'hotel/DELETE_HOTEL',
  RESET: 'hotel/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHotelMySuffix>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type HotelMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: HotelMySuffixState = initialState, action): HotelMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_HOTEL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HOTEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HOTEL):
    case REQUEST(ACTION_TYPES.UPDATE_HOTEL):
    case REQUEST(ACTION_TYPES.DELETE_HOTEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_HOTEL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HOTEL):
    case FAILURE(ACTION_TYPES.CREATE_HOTEL):
    case FAILURE(ACTION_TYPES.UPDATE_HOTEL):
    case FAILURE(ACTION_TYPES.DELETE_HOTEL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOTEL_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOTEL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HOTEL):
    case SUCCESS(ACTION_TYPES.UPDATE_HOTEL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HOTEL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/hotels';

// Actions

export const getEntities: ICrudGetAllAction<IHotelMySuffix> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_HOTEL_LIST,
    payload: axios.get<IHotelMySuffix>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IHotelMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HOTEL,
    payload: axios.get<IHotelMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHotelMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HOTEL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHotelMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HOTEL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHotelMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HOTEL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
