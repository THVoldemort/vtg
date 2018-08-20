import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHotelRoomMySuffix, defaultValue } from 'app/shared/model/hotel-room-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_HOTELROOM_LIST: 'hotelRoom/FETCH_HOTELROOM_LIST',
  FETCH_HOTELROOM: 'hotelRoom/FETCH_HOTELROOM',
  CREATE_HOTELROOM: 'hotelRoom/CREATE_HOTELROOM',
  UPDATE_HOTELROOM: 'hotelRoom/UPDATE_HOTELROOM',
  DELETE_HOTELROOM: 'hotelRoom/DELETE_HOTELROOM',
  RESET: 'hotelRoom/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHotelRoomMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HotelRoomMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: HotelRoomMySuffixState = initialState, action): HotelRoomMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_HOTELROOM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HOTELROOM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HOTELROOM):
    case REQUEST(ACTION_TYPES.UPDATE_HOTELROOM):
    case REQUEST(ACTION_TYPES.DELETE_HOTELROOM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_HOTELROOM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HOTELROOM):
    case FAILURE(ACTION_TYPES.CREATE_HOTELROOM):
    case FAILURE(ACTION_TYPES.UPDATE_HOTELROOM):
    case FAILURE(ACTION_TYPES.DELETE_HOTELROOM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOTELROOM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOTELROOM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HOTELROOM):
    case SUCCESS(ACTION_TYPES.UPDATE_HOTELROOM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HOTELROOM):
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

const apiUrl = 'api/hotel-rooms';

// Actions

export const getEntities: ICrudGetAllAction<IHotelRoomMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HOTELROOM_LIST,
  payload: axios.get<IHotelRoomMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHotelRoomMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HOTELROOM,
    payload: axios.get<IHotelRoomMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHotelRoomMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HOTELROOM,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHotelRoomMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HOTELROOM,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHotelRoomMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HOTELROOM,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
