import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hotel-room-my-suffix.reducer';
import { IHotelRoomMySuffix } from 'app/shared/model/hotel-room-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHotelRoomMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class HotelRoomMySuffixDetail extends React.Component<IHotelRoomMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hotelRoomEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="vtgApp.hotelRoom.detail.title">HotelRoom</Translate> [<b>{hotelRoomEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="type">
                <Translate contentKey="vtgApp.hotelRoom.type">Type</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.type}</dd>
            <dt>
              <span id="introduction">
                <Translate contentKey="vtgApp.hotelRoom.introduction">Introduction</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.introduction}</dd>
            <dt>
              <span id="convenient">
                <Translate contentKey="vtgApp.hotelRoom.convenient">Convenient</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.convenient}</dd>
            <dt>
              <span id="cancelFeeDay1">
                <Translate contentKey="vtgApp.hotelRoom.cancelFeeDay1">Cancel Fee Day 1</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.cancelFeeDay1}</dd>
            <dt>
              <span id="cancelFeeDay2">
                <Translate contentKey="vtgApp.hotelRoom.cancelFeeDay2">Cancel Fee Day 2</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.cancelFeeDay2}</dd>
            <dt>
              <span id="cancelFeeTime1">
                <Translate contentKey="vtgApp.hotelRoom.cancelFeeTime1">Cancel Fee Time 1</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.cancelFeeTime1}</dd>
            <dt>
              <span id="cancelFeeTime2">
                <Translate contentKey="vtgApp.hotelRoom.cancelFeeTime2">Cancel Fee Time 2</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.cancelFeeTime2}</dd>
            <dt>
              <span id="ratingId">
                <Translate contentKey="vtgApp.hotelRoom.ratingId">Rating Id</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.ratingId}</dd>
            <dt>
              <span id="numOfAdult">
                <Translate contentKey="vtgApp.hotelRoom.numOfAdult">Num Of Adult</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.numOfAdult}</dd>
            <dt>
              <span id="numOfChild">
                <Translate contentKey="vtgApp.hotelRoom.numOfChild">Num Of Child</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.numOfChild}</dd>
            <dt>
              <span id="priceEst">
                <Translate contentKey="vtgApp.hotelRoom.priceEst">Price Est</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.priceEst}</dd>
            <dt>
              <span id="priceAct">
                <Translate contentKey="vtgApp.hotelRoom.priceAct">Price Act</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.priceAct}</dd>
            <dt>
              <span id="quantity">
                <Translate contentKey="vtgApp.hotelRoom.quantity">Quantity</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.quantity}</dd>
            <dt>
              <span id="filePath1">
                <Translate contentKey="vtgApp.hotelRoom.filePath1">File Path 1</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.filePath1}</dd>
            <dt>
              <span id="filePath2">
                <Translate contentKey="vtgApp.hotelRoom.filePath2">File Path 2</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.filePath2}</dd>
            <dt>
              <span id="filePath3">
                <Translate contentKey="vtgApp.hotelRoom.filePath3">File Path 3</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.filePath3}</dd>
            <dt>
              <span id="filePath4">
                <Translate contentKey="vtgApp.hotelRoom.filePath4">File Path 4</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.filePath4}</dd>
            <dt>
              <span id="filePath5">
                <Translate contentKey="vtgApp.hotelRoom.filePath5">File Path 5</Translate>
              </span>
            </dt>
            <dd>{hotelRoomEntity.filePath5}</dd>
          </dl>
          <Button tag={Link} to="/entity/hotel-room-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/hotel-room-my-suffix/${hotelRoomEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ hotelRoom }: IRootState) => ({
  hotelRoomEntity: hotelRoom.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HotelRoomMySuffixDetail);
