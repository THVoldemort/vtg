import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPlaceMySuffix, defaultValue } from 'app/shared/model/place-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_PLACE_LIST: 'place/FETCH_PLACE_LIST',
  FETCH_PLACE: 'place/FETCH_PLACE',
  CREATE_PLACE: 'place/CREATE_PLACE',
  UPDATE_PLACE: 'place/UPDATE_PLACE',
  DELETE_PLACE: 'place/DELETE_PLACE',
  RESET: 'place/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPlaceMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PlaceMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: PlaceMySuffixState = initialState, action): PlaceMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PLACE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PLACE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PLACE):
    case REQUEST(ACTION_TYPES.UPDATE_PLACE):
    case REQUEST(ACTION_TYPES.DELETE_PLACE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PLACE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PLACE):
    case FAILURE(ACTION_TYPES.CREATE_PLACE):
    case FAILURE(ACTION_TYPES.UPDATE_PLACE):
    case FAILURE(ACTION_TYPES.DELETE_PLACE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLACE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLACE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PLACE):
    case SUCCESS(ACTION_TYPES.UPDATE_PLACE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PLACE):
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

const apiUrl = 'api/places';

// Actions

export const getEntities: ICrudGetAllAction<IPlaceMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PLACE_LIST,
  payload: axios.get<IPlaceMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPlaceMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PLACE,
    payload: axios.get<IPlaceMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPlaceMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PLACE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPlaceMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PLACE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPlaceMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PLACE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
