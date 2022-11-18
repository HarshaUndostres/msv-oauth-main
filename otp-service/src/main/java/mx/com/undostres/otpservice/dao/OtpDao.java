package mx.com.undostres.otpservice.dao;

import mx.com.undostres.otpservice.models.OtpOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public interface OtpDao extends JpaRepository<OtpOrder, Integer> {



    /**
     * @return array - array such that
     * array[i] = array(name, sendOtpFunction, verifyOtpFunction, name of input mobile, name of input code, name of hidden input to request the code, name of hidden input to validate code)
     */
    //ArrayList getOtpOrder() throws Exception;
    @Query(
            value = "SELECT name FROM otp_order ORDER BY id ASC",
            nativeQuery = true
    )
    List<String> getOtpOrder();



    /**
     * @param mobile - string
     * @param provider - number
     * @return string|null - the id of the row inserted, null if insert failed
     */
    //String insert(String mobile, Number provider) throws Exception;


    /**
     * @param id - id of the row to update
     * @param apiResponse - string
     * @param status - number
     * @return bool - if the update was successful
     */
    //boolean  updateAfterSend(long id, String apiResponse, Number status);


    /**
     * @param id - id of the row to update
     * @param apiResponse - string
     * @param status - number
     * @return bool - if the update was successful
     */
    //boolean updateAfterVerify(long id, String apiResponse, Number status);

    /**
     * Used to move old admin data from tb_wallet.request_id_nexmo
     * @return array - represents information about the modified users
     */
    //ArrayList moveOldAdminData();

    /**
     * @param  mobile
     * @param  hours
     * @return int - number of otp sent to the mobile within the last hours
     */
  // int getMobileAttempts(String mobile, int hours);
   //int getMobileAttempts(String mobile);



}
